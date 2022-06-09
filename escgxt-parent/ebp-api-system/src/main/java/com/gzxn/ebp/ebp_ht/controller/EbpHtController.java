package com.gzxn.ebp.ebp_ht.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsqQd;
import com.gzxn.ebp.ebp_cgsq.service.IEbpCgsqQdService;
import com.gzxn.ebp.ebp_cgsq.service.IEbpCgsqService;
import com.gzxn.ebp.ebp_ht.entity.EbpHt;
import com.gzxn.ebp.ebp_ht.service.IEbpHtService;
import com.gzxn.ebp.ebp_jfly.entity.EbpJfsy;
import com.gzxn.ebp.ebp_jfly.service.IEbpJfsyService;
import com.gzxn.ebp.ebp_ysd.service.IEbpYsdService;
import com.gzxn.ebp.ebp_zbjg.entity.EbpZbjg;
import com.gzxn.ebp.ebp_zbjg.service.IEbpZbjgService;
import com.gzxn.ebp.lcgl.entity.Lcb;
import com.gzxn.ebp.lcgl.service.ILcbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.gzxn.ebp.ebp_ht.controller
 * @ClassName: EbpHtController
 * @Description: 合同表-控制器
 * @author: CodeBird
 * @date: 2022-03-26 14:02:50
 */
@Api(tags = "合同表")
@RestController
@RequestMapping("/ebp_ht/ebpHt")
public class EbpHtController extends BaseController {

    @Autowired
    private IEbpHtService iEbpHtService;
    @Autowired
    private IEbpCgsqService iEbpCgsqService;
    @Autowired
    private IEbpYsdService iEbpYsdService;
    @Autowired
    private IEbpZbjgService iEbpZbjgService;
    @Autowired
    private IEbpCgsqQdService iEbpCgsqQdService;
    @Autowired
    private IEbpJfsyService iEbpJfsyService;
    @Autowired
    private ILcbService iLcbService;

    private EbpHt ebpHt;

    @ApiOperation(value = "列表")
//    @RequiresPermissions("4000010001")
    @GetMapping("/list")
    public ResponseBean<Page> list(Page page,
                                   @ApiParam(value = "route_name") @RequestParam(name = "route_name", required = false) String route_name,
                                   @ApiParam(value = "route_path") @RequestParam(name = "route_path", required = false) String route_path,
                                   @ApiParam(value = "采购项目编号") @RequestParam(name = "cgbh", required = false) String cgbh,
                                   @ApiParam(value = "采购项目名称") @RequestParam(name = "xmmc", required = false) String xmmc,
                                   @ApiParam(value = "采购项目编号") @RequestParam(name = "htbh", required = false) String htbh,
                                   @ApiParam(value = "采购项目名称") @RequestParam(name = "htmc", required = false) String htmc) {
        QueryWrapper<EbpHt> htQueryWrapper = new QueryWrapper<EbpHt>();
        QueryWrapper<EbpCgsq> cgsqQueryWrapper = new QueryWrapper<EbpCgsq>();
        // 获取登录用户uid
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        if (StrUtil.isNotBlank(cgbh)) {
            cgsqQueryWrapper.like("ebp_cgsq.cgbh", cgbh);
        }
        if (StrUtil.isNotBlank(xmmc)) {
            cgsqQueryWrapper.like("ebp_cgsq.xmmc", xmmc);
        }
        if (StrUtil.isNotBlank(htbh)) {
            htQueryWrapper.like("ebp_ht.htbh", htbh);
        }
        if (StrUtil.isNotBlank(htmc)) {
            htQueryWrapper.like("ebp_ht.htmc", htmc);
        }
        Page listPage = new Page<>();
        // 查询-合同-待拟稿
        if ("ebp_ht_dng".equals(route_path)) {
            cgsqQueryWrapper.eq("ebp_ysd.is_success", "1");
            cgsqQueryWrapper.isNull("ebp_ht.jgid");
            if (!"1".equals(uid)) {
                cgsqQueryWrapper.eq("ebp_cgsq.crer", uid);
            }
            listPage = iEbpHtService.dngPageVo(page, cgsqQueryWrapper);
        }
        // 查询-合同-未提交
        if ("ebp_ht_wtj".equals(route_path)) {
            if (!"1".equals(uid)) {
                htQueryWrapper.eq("ht.crer", uid);
            }
            listPage = iEbpHtService.wtjPageVo(page, htQueryWrapper);
        }
        // 查询-合同-已提交
        if ("ebp_ht_ytj".equals(route_path)) {
            if (!"1".equals(uid) && !"ce5a9afa75505ac4d827eb6c6dfc7ffb".equals(uid)) {
                htQueryWrapper.eq("ht.crer", uid);
            }
            listPage = iEbpHtService.ytjPageVo(page, htQueryWrapper);
        }
        // 查询-合同-未审核
        if ("ebp_ht_wsh".equals(route_path)) {
            if (!"1".equals(uid)) {
                htQueryWrapper.eq("dqclrid", uid);
            }
            listPage = iEbpHtService.wshPageVo(page, htQueryWrapper);
        }
        // 查询-合同-已审核
        if ("ebp_ht_ysh".equals(route_path)) {
            if ("1".equals(uid)) {
                htQueryWrapper.inSql("ebp_ht.id", "SELECT bdid FROM lcb");
            } else {
                htQueryWrapper.inSql("ebp_ht.id", "SELECT bdid FROM lcb WHERE lcb.crer = '" + uid + "'");
            }
            listPage = iEbpHtService.yshPageVo(page, htQueryWrapper);
        }
        return ResponseBean.Success(listPage);
    }

    @ApiOperation(value = "添加")
//    @RequiresPermissions("4000010005")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody Map<String, Object> record) {
        boolean flag;
        flag = this.saveHt(record);
        if (flag) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "提交审核")
    @PostMapping("/htTjsh")
    public ResponseBean htTjsh(@RequestBody Map<String, Object> record) {
        boolean flag;
        flag = this.saveHt(record);
        if (flag) {
            flag = this.tjsh(ebpHt);
            return flag ? ResponseBean.Success() : ResponseBean.Fail();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "去编辑")
//    @RequiresPermissions("4000032001")
    @GetMapping("/toUpdate")
    public ResponseBean<Map<String, Object>> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        EbpHt ebpHt = iEbpHtService.getById(id);
        // 根据jgid查找招标结果
        EbpZbjg ebpZbjg = iEbpZbjgService.getById(ebpHt.getJgid());
        // 根据sbid查找采购申请清单
        EbpCgsqQd ebpCgsqQd = iEbpCgsqQdService.getById(ebpZbjg.getSbid());
        // 根据id查找采购申请
        EbpCgsq ebpCgsq = iEbpCgsqService.getById(ebpCgsqQd.getCgsqid());
        // 根据cgsqid查找经费
        QueryWrapper<EbpJfsy> jfsyQueryWrapper = new QueryWrapper<EbpJfsy>();
        jfsyQueryWrapper.eq("cgsqid", ebpCgsq.getId());
        EbpJfsy ebpJfsy = iEbpJfsyService.getOne(jfsyQueryWrapper);
        // 封装数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("htParameter", ebpHt);
        map.put("zbjgParameter", ebpZbjg);
        map.put("cgsqqdParameter", ebpCgsqQd);
        map.put("cgsqParameter", ebpCgsq);
        map.put("jfsyParameter", ebpJfsy);
        if (ObjectUtil.isNotNull(map)) {
            return ResponseBean.Success(map);
        } else {
            return ResponseBean.Fail();
        }
    }


    @ApiOperation(value = "编辑")
//    @RequiresPermissions("4000032001")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Map<String, Object> record) {
        if (iEbpHtService.saveData(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "拟稿")
//    @RequiresPermissions("4000025001")
    @GetMapping("/toHtDraft")
    public ResponseBean<Map<String, Object>> toHtDraft(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        // 根据id查找采购申请
        EbpCgsq ebpCgsq = iEbpCgsqService.getById(id);
        // 根据cgsqid查找采购申请清单
        QueryWrapper<EbpCgsqQd> cgsqQdWrapper = new QueryWrapper<EbpCgsqQd>();
        cgsqQdWrapper.eq("cgsqid", id);
        EbpCgsqQd ebpCgsqQd = iEbpCgsqQdService.getOne(cgsqQdWrapper);
        // 根据cgsqid查找经费
        QueryWrapper<EbpJfsy> jfsyQueryWrapper = new QueryWrapper<EbpJfsy>();
        jfsyQueryWrapper.eq("cgsqid", id);
        EbpJfsy ebpJfsy = iEbpJfsyService.getOne(jfsyQueryWrapper);
        // 根据sbid查找招标结果
        QueryWrapper<EbpZbjg> zbjgQueryWrapper = new QueryWrapper<EbpZbjg>();
        zbjgQueryWrapper.eq("sbid", ebpCgsqQd.getId());
        EbpZbjg ebpZbjg = iEbpZbjgService.getOne(zbjgQueryWrapper);
        // 封装数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("cgsqParameter", ebpCgsq);
        map.put("cgsqqdParameter", ebpCgsqQd);
        map.put("jfsyParameter", ebpJfsy);
        map.put("zbjgParameter", ebpZbjg);
        if (ObjectUtil.isNotNull(map)) {
            return ResponseBean.Success(map);
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "删除")
    @RequiresPermissions("4000010015")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iEbpHtService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    /**
     * 保存采购申请填报信息
     *
     * @param record
     * @return true/false
     */
    public boolean saveHt(Map<String, Object> record) {
        boolean flag = false;
        EbpCgsq cgsqParameter = JSON.toJavaObject((JSON) record.get("cgsqParameter"), EbpCgsq.class);
        EbpCgsqQd cgsqqdParameter = JSON.toJavaObject((JSON) record.get("cgsqqdParameter"), EbpCgsqQd.class);
        EbpJfsy jfsyParameter = JSON.toJavaObject((JSON) record.get("jfsyParameter"), EbpJfsy.class);
        EbpZbjg ebpZbjg = JSON.toJavaObject((JSON) record.get("zbjgParameter"), EbpZbjg.class);
        EbpHt htParameter = JSON.toJavaObject((JSON) record.get("htParameter"), EbpHt.class);
        String htId = htParameter.getId();
        // 合同项目id为空则添加信息，不为空则保存信息
        if (ObjectUtil.isEmpty(htId)) {
            // 获取采购项目编号与采购清单编号
            if (ObjectUtil.isEmpty(htParameter.getHtbh())) {
                String htbh = iEbpHtService.getHtLsh();
                htParameter.setHtbh(htbh);
            }
            if (iEbpHtService.save(htParameter)) {
                flag = true;
            }
        } else {
            iEbpHtService.updateById(htParameter);
            flag = true;
        }
        this.ebpHt = htParameter;
        return flag;
    }

    /**
     * 提交审核
     *
     * @param ebpHt
     * @return true/false
     */
    public boolean tjsh(EbpHt ebpHt) {
        boolean flag = false;
        String htId = ebpHt.getId();
        // 查询是否已开启流程
        QueryWrapper<Lcb> queryHtLcById = new QueryWrapper<Lcb>();
        queryHtLcById.like("bdid", htId);
        List<Lcb> htLcList = iLcbService.list(queryHtLcById);
        Integer sxh = htLcList.size();
        // 查询采购申请流程
        QueryWrapper<Lcb> queryHtLcByLcid = new QueryWrapper<Lcb>();
        queryHtLcByLcid.like("lcid", '2');
        queryHtLcByLcid.orderByAsc("sxh");
        List<Lcb> lcbList = iLcbService.list(queryHtLcByLcid);
        Lcb lcb;
        // 获取登录用户uid
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        // 开启流程，流程表上增加一条已提交记录
        lcb = new Lcb();
        lcb.setLcmc("合同审核流程");
        lcb.setUid(uid);
        lcb.setBdid(htId);
        lcb.setBdname("ebp_ht");
        lcb.setJdmc("申请人提交");
        lcb.setShjg(1);
        lcb.setShyj("提交");
        if (htLcList.size() > 0) {
            lcb.setSxh(sxh.toString());
        } else {
            lcb.setSxh("0");
        }
        iLcbService.save(lcb);
        ebpHt.setBpmStatus("2");
        ebpHt.setDqjdid(lcbList.get(0).getId());
        ebpHt.setDqclrid(lcbList.get(0).getUid());
        iEbpHtService.updateById(ebpHt);
        flag = true;
        return flag;
    }

}
