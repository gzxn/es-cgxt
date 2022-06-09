package com.gzxn.ebp.ebp_cgsq.controller;

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
import com.gzxn.ebp.ebp_jfly.entity.EbpJfly;
import com.gzxn.ebp.ebp_jfly.entity.EbpJfsy;
import com.gzxn.ebp.ebp_jfly.service.IEbpJflyService;
import com.gzxn.ebp.ebp_jfly.service.IEbpJfsyService;
import com.gzxn.ebp.lcgl.entity.Lcb;
import com.gzxn.ebp.lcgl.service.ILcbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Copyright  2022-03-26 QLH. Tech Ltd. All rights reserved.
 *
 * @Package: com.gzxn.ebp.ebp_cgsq.controller
 * @ClassName: EbpCgsqController
 * @Description: 采购申请表-控制器
 * @author: CodeBird
 * @date: 2022-03-26 13:57:47
 */
@Api(tags = "采购申请表")
@RestController
@RequestMapping("/ebp_cgsq/ebpCgsq")
public class EbpCgsqController extends BaseController {

    @Autowired
    private IEbpCgsqService iEbpCgsqService;
    @Autowired
    private IEbpCgsqQdService iEbpCgsqQdService;
    @Autowired
    private IEbpJflyService iEbpJflyService;
    @Autowired
    private IEbpJfsyService iEbpJfsyService;
    @Autowired
    private ILcbService iLcbService;

    private EbpCgsq ebpCgsq;

    @ApiOperation(value = "列表")
//    @RequiresPermissions("3000010001")
    @GetMapping("/list")
    public ResponseBean<Page<EbpCgsq>> list(Page page,
                                            @ApiParam(value = "route_name") @RequestParam(name = "route_name", required = false) String route_name,
                                            @ApiParam(value = "route_path") @RequestParam(name = "route_path", required = false) String route_path,
                                            @ApiParam(value = "采购项目编号") @RequestParam(name = "cgbh", required = false) String cgbh,
                                            @ApiParam(value = "采购项目名称") @RequestParam(name = "xmmc", required = false) String xmmc) {
       /* Page page = JSON.toJavaObject((JSON) record.get("page"), Page.class);
        Object routeJson = record.get("route");*/
        QueryWrapper<EbpCgsq> queryWrapper = new QueryWrapper<EbpCgsq>();
        // 获取登录用户uid
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        if (StrUtil.isNotBlank(cgbh)) {
            queryWrapper.like("cgbh", cgbh);
        }
        if (StrUtil.isNotBlank(xmmc)) {
            queryWrapper.like("xmmc", xmmc);
        }
        Page<EbpCgsq> cgsqPage = new Page<>();
        // 查询-采购申请-未提交的数据
        if ("ebp_cgsq_wtj".equals(route_path)) {
            if (!"1".equals(uid)) {
                queryWrapper.eq("cgsq.crer",uid);
            }
            cgsqPage = iEbpCgsqService.wtjPageVo(page, queryWrapper);
        }
        // 查询-采购申请-已提交提交的数据
        if ("ebp_cgsq_ytj".equals(route_path)) {
            if (!"1".equals(uid)) {
                queryWrapper.eq("cgsq.crer",uid);
            }
            cgsqPage = iEbpCgsqService.ytjPageVo(page, queryWrapper);
        }
        // 查询-采购申请-未审核的数据
        if ("ebp_cgsq_wsh".equals(route_path)) {
            if (!"1".equals(uid)) {
                queryWrapper.eq("dqclr", uid);
            }
            cgsqPage = iEbpCgsqService.wshPageVo(page, queryWrapper);
        }
        // 查询-采购申请-已审核的数据
        if ("ebp_cgsq_ysh".equals(route_path)) {
            if ("1".equals(uid)) {
                queryWrapper.inSql("ebp_cgsq.id","SELECT bdid FROM lcb");
            } else {
                queryWrapper.inSql("ebp_cgsq.id","SELECT bdid FROM lcb WHERE lcb.crer = '" + uid + "'");
            }
            cgsqPage = iEbpCgsqService.yshPageVo(page, queryWrapper);
        }
        if ("ebp_cgsq_list".equals(route_path)) {
            cgsqPage = iEbpCgsqService.pageVo(page, queryWrapper);
        }
        return ResponseBean.Success(cgsqPage);
    }

    @ApiOperation(value = "添加")
//    @RequiresPermissions("3000010005")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody Map<String, Object> record) {
        boolean flag;
        flag = this.saveCgsq(record);
        if (flag) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "提交审核")
    @PostMapping("/cgsqTjsh")
    public ResponseBean cgsqTjsh(@RequestBody Map<String, Object> record) {
        boolean flag;
        flag = this.saveCgsq(record);
        if (flag) {
            flag = this.tjsh(ebpCgsq);
            return flag ? ResponseBean.Success() : ResponseBean.Fail();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "去编辑")
//    @RequiresPermissions("3000010010")
    @GetMapping("/toUpdate")
    public ResponseBean<Map<String, Object>> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        EbpCgsq ebpCgsq = iEbpCgsqService.getById(id);
        QueryWrapper<EbpCgsqQd> cgsqQdWrapper = new QueryWrapper<EbpCgsqQd>();
        cgsqQdWrapper.eq("cgsqid", id);
        EbpCgsqQd ebpCgsqQd = iEbpCgsqQdService.getOne(cgsqQdWrapper);
        QueryWrapper<EbpJfsy> jfsyWrapper = new QueryWrapper<EbpJfsy>();
        jfsyWrapper.eq("cgsqid", id);
        EbpJfsy ebpJfsy = iEbpJfsyService.getOne(jfsyWrapper);
        // 封装数据
        List<Map<String, Object>> list = new ArrayList();
        Map<String, Object> map = new HashMap<>(16);
        map.put("cgsqParameter", ebpCgsq);
        map.put("cgsqqdParameter", ebpCgsqQd);
        map.put("jfsyParameter", ebpJfsy);
        list.add(map);
        if (ObjectUtil.isNotNull(map)) {
            return ResponseBean.Success(map);
        } else {
            return ResponseBean.Fail();
        }
    }


    @ApiOperation(value = "编辑")
//    @RequiresPermissions("3000010010")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Map<String, Object> record) {
        if (iEbpCgsqService.saveData(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "删除")
    @RequiresPermissions("3000010015")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iEbpCgsqService.removeByIds(Arrays.asList(ids))) {
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
    public boolean saveCgsq(Map record) {
        boolean flag = false;
        EbpCgsq cgsqParameter = JSON.toJavaObject((JSON) record.get("cgsqParameter"), EbpCgsq.class);
        EbpCgsqQd cgsqqdParameter = JSON.toJavaObject((JSON) record.get("cgsqqdParameter"), EbpCgsqQd.class);
        EbpJfsy jfsyParameter = JSON.toJavaObject((JSON) record.get("jfsyParameter"), EbpJfsy.class);
        String cgsqId = cgsqParameter.getId();
        // 采购项目id为空则添加信息，不为空则保存信息
        if (ObjectUtil.isEmpty(cgsqId)) {
            // 获取采购项目编号与采购清单编号
            if (ObjectUtil.isEmpty(cgsqParameter.getCgbh())) {
                String cgbh = iEbpCgsqService.getCgsqLsh();
                String hwhb = iEbpCgsqService.getCgsqqdLsh(cgbh);
                cgsqParameter.setCgbh(cgbh);
                cgsqqdParameter.setHwbh(hwhb);
            }
            EbpJfly jfly = iEbpJflyService.getById(jfsyParameter.getJflyid());
            if (iEbpCgsqService.save(cgsqParameter)) {
                cgsqId = cgsqParameter.getId();
                // 清单数据
                cgsqqdParameter.setCgsqid(cgsqId);
                // 经费使用数据
                jfsyParameter.setCgsqid(cgsqId);
                if (!StringUtils.isEmpty(jfly)) {
                    jfsyParameter.setYsmc(jfly.getYsmc());
                    jfsyParameter.setYsbm(jfly.getYsbm());
                    jfsyParameter.setJfType(jfly.getJfType());
                }
                if (iEbpCgsqQdService.save(cgsqqdParameter)) {
                    iEbpJfsyService.save(jfsyParameter);
                    flag = true;
                }
            }
        } else {
            iEbpCgsqService.updateById(cgsqParameter);
            iEbpCgsqQdService.updateById(cgsqqdParameter);
            iEbpJfsyService.updateById(jfsyParameter);
            flag = true;
        }
        this.ebpCgsq = cgsqParameter;
        return flag;
    }

    /**
     * 提交审核
     *
     * @param ebpCgsq
     * @return true/false
     */
    public boolean tjsh(EbpCgsq ebpCgsq) {
        boolean flag = false;
        String cgsqId = ebpCgsq.getId();
        // 查询是否已开启流程
        QueryWrapper<Lcb> queryCgsqLcById = new QueryWrapper<Lcb>();
        queryCgsqLcById.like("bdid", cgsqId);
        List<Lcb> cgsqLcList = iLcbService.list(queryCgsqLcById);
        Integer sxh = cgsqLcList.size();
        // 查询采购申请流程
        QueryWrapper<Lcb> queryCgsqLcByLcid = new QueryWrapper<Lcb>();
        queryCgsqLcByLcid.like("lcid", '1');
        queryCgsqLcByLcid.orderByAsc("sxh");
        List<Lcb> lcbList = iLcbService.list(queryCgsqLcByLcid);
        Lcb lcb;
        // 获取登录用户uid
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        // 开启流程，流程表上增加一条已提交记录
        lcb = new Lcb();
        lcb.setLcmc("采购申请审核流程");
        lcb.setUid(uid);
        lcb.setBdid(cgsqId);
        lcb.setBdname("ebp_cgsq");
        lcb.setJdmc("申请人提交");
        lcb.setShjg(1);
        lcb.setShyj("提交");
        if (cgsqLcList.size() > 0) {
            lcb.setSxh(sxh.toString());
        } else {
            lcb.setSxh("0");
        }
        iLcbService.save(lcb);
        ebpCgsq.setBpmStatus("2");
        ebpCgsq.setDqjd(lcbList.get(0).getId());
        ebpCgsq.setDqclr(lcbList.get(0).getUid());
        iEbpCgsqService.updateById(ebpCgsq);
        flag = true;
        return flag;
    }

}
