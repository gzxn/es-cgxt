package com.gzxn.ebp.lcgl.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import com.gzxn.ebp.ebp_cgsq.service.IEbpCgsqService;
import com.gzxn.ebp.ebp_ht.entity.EbpHt;
import com.gzxn.ebp.ebp_ht.service.IEbpHtService;
import com.gzxn.ebp.lcgl.entity.Lcb;
import com.gzxn.ebp.lcgl.service.ILcbService;
import com.gzxn.ebp.system.entity.User;
import com.gzxn.ebp.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.gzxn.ebp.lcgl.controller
 * @ClassName: LcbController
 * @Description: -控制器
 * @author: CodeBird
 * @date: 2022-05-02 11:53:24
 */
@Api(tags = "")
@RestController
@RequestMapping("/lcgl/lcb")
public class LcbController extends BaseController {

    @Autowired
    private ILcbService iLcbService;
    @Autowired
    private IEbpCgsqService iEbpCgsqService;
    @Autowired
    private IEbpHtService iEbpHtService;
    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResponseBean<Page<Lcb>> list(Page page,
                                        @ApiParam(value = "表单主键") @RequestParam(name = "bdid", required = false) String bdid) {
        QueryWrapper<Lcb> queryWrapper = new QueryWrapper<Lcb>();
        queryWrapper.like("bdid", bdid);
//        queryWrapper.orderByAsc("sxh");
        queryWrapper.orderByAsc("crtm");
        Page<Lcb> lcbPage = iLcbService.page(page, queryWrapper);
        List<Lcb> lcbList = lcbPage.getRecords();
        String userId = "";
        for (int i=0; i<lcbList.size(); i++) {
            userId = lcbList.get(i).getCrer();
            User user = iUserService.getById(userId);
            lcbList.get(i).setCrer(user.getName());
        }
        return ResponseBean.Success(lcbPage);
    }

    @ApiOperation(value = "添加")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody Lcb record) {
        if (iLcbService.save(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "去编辑")
    @GetMapping("/toUpdate")
    public ResponseBean<Lcb> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        Lcb lcb = iLcbService.getById(id);
        if (ObjectUtil.isNotNull(lcb)) {
            return ResponseBean.Success(lcb);
        } else {
            return ResponseBean.Fail();
        }
    }


    @ApiOperation(value = "审核")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Map record) {
        boolean flag = this.audit(record);
//        boolean flag = true;
        if (flag) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iLcbService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "获取当前审核节点")
    @GetMapping("/getShNode")
    public ResponseBean<Lcb> getShNode(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        Lcb lcb = iLcbService.getById(id);
        if (ObjectUtil.isNotNull(lcb)) {
            return ResponseBean.Success(lcb);
        } else {
            return ResponseBean.Fail();
        }
    }

    /**
     * 审核
     *
     * @param record
     * @return true/false
     */
    public boolean audit(Map<String, Object> record) {
        EbpCgsq cgsqParameter = JSON.toJavaObject((JSON) record.get("cgsqParameter"), EbpCgsq.class);
        EbpHt htParameter = JSON.toJavaObject((JSON) record.get("htParameter"), EbpHt.class);
        Lcb lcbParameter = JSON.toJavaObject((JSON) record.get("lcbParameter"), Lcb.class);
        boolean flag = false;
        // 表单id
        String bdid = "";
        // 当前审核节点id
        String dqjd = "";
        if (record.get("cgsqParameter") != null) {
            bdid = cgsqParameter.getId();
            dqjd = cgsqParameter.getDqjd();
        }
        if (record.get("htParameter") != null) {
            bdid = htParameter.getId();
            dqjd = htParameter.getDqjdid();
        }
        // 获取登录用户uid
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        // 当前流程
        Lcb dqlc = iLcbService.getById(dqjd);
        String lcid = dqlc.getLcid();
        // 整个审核流程
        QueryWrapper<Lcb> queryLcByLcid = new QueryWrapper<Lcb>();
        queryLcByLcid.like("lcid", lcid);
        queryLcByLcid.orderByAsc("sxh");
        List<Lcb> lcbList = iLcbService.list(queryLcByLcid);
        // 整个审核历史流程
        QueryWrapper<Lcb> queryLcByBdid = new QueryWrapper<Lcb>();
        queryLcByBdid.like("bdid", bdid);
        queryLcByBdid.orderByAsc("sxh");
        List<Lcb> bdLcbList = iLcbService.list(queryLcByBdid);
        Integer sxh = bdLcbList.size();
        // 将要插入的审核历史
        Lcb auditHistory = new Lcb();
        // 将要更新的表单数据
        UpdateWrapper<EbpCgsq> updateCgsqWrapper = new UpdateWrapper<EbpCgsq>();
        UpdateWrapper<EbpHt> updateHtWrapper = new UpdateWrapper<EbpHt>();
        switch (lcid) {
            case "1": // 采购申请流程
                // 插入审核历史
                auditHistory.setLcmc("采购申请审核流程");
                auditHistory.setUid(uid);
                auditHistory.setBdid(bdid);
                auditHistory.setBdname("ebp_cgsq");
                auditHistory.setJdid(dqlc.getId());
                auditHistory.setJdmc(dqlc.getJdmc());
                auditHistory.setShjg(lcbParameter.getShjg());
                auditHistory.setShyj(lcbParameter.getShyj());
                auditHistory.setSxh(sxh.toString());
                iLcbService.save(auditHistory);
                // 更新表单数据
                // 审核通过
                if (lcbParameter.getShjg() == 1) {
                    if ("1".equals(dqlc.getSxh())) {
                        lcbList.remove(0);
                        updateCgsqWrapper.set("dqjd", lcbList.get(0).getId());
                        updateCgsqWrapper.set("dqclr", lcbList.get(0).getUid());
                    } else if ("2".equals(dqlc.getSxh())) {
                        updateCgsqWrapper.set("dqjd", null);
                        updateCgsqWrapper.set("dqclr", null);
                        updateCgsqWrapper.set("bpm_status", "5");
                    } else {
                        updateCgsqWrapper.set("dqjd", null);
                        updateCgsqWrapper.set("dqclr", null);
                        updateCgsqWrapper.set("bpm_status", "1");
                    }
                } else {
                    updateCgsqWrapper.set("dqjd", null);
                    updateCgsqWrapper.set("dqclr", null);
                    updateCgsqWrapper.set("bpm_status", "1");
                }
                updateCgsqWrapper.eq("id", bdid);
                iEbpCgsqService.update(updateCgsqWrapper);
                flag = true;
                break;
            case "2": // 合同流程
                // 插入审核历史
                auditHistory.setLcmc("合同审核流程");
                auditHistory.setUid(uid);
                auditHistory.setBdid(bdid);
                auditHistory.setBdname("ebp_ht");
                auditHistory.setJdid(dqlc.getId());
                auditHistory.setJdmc(dqlc.getJdmc());
                auditHistory.setShjg(lcbParameter.getShjg());
                auditHistory.setShyj(lcbParameter.getShyj());
                auditHistory.setSxh(sxh.toString());
                iLcbService.save(auditHistory);
                // 更新表单数据
                // 审核通过
                if (lcbParameter.getShjg() == 1) {
                    if ("1".equals(dqlc.getSxh())) {
                        lcbList.remove(0);
                        updateHtWrapper.set("dqjdid", lcbList.get(0).getId());
                        updateHtWrapper.set("dqclrid", lcbList.get(0).getUid());
                    } else if ("2".equals(dqlc.getSxh())) {
                        updateHtWrapper.set("dqjdid", null);
                        updateHtWrapper.set("dqclrid", null);
                        updateHtWrapper.set("bpm_status", "5");
                    } else {
                        updateHtWrapper.set("dqjdid", null);
                        updateHtWrapper.set("dqclrid", null);
                        updateHtWrapper.set("bpm_status", "1");
                    }
                } else {
                    updateHtWrapper.set("dqjdid", null);
                    updateHtWrapper.set("dqclrid", null);
                    updateHtWrapper.set("bpm_status", "1");
                }
                updateHtWrapper.eq("id", bdid);
                iEbpHtService.update(updateHtWrapper);
                flag = true;
                break;
        }

        return flag;
    }


}
