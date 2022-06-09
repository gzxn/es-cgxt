package com.gzxn.ebp.ebp_ysd.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_ysd.entity.EbpYsd;
import com.gzxn.ebp.ebp_ysd.service.IEbpYsdService;
import com.gzxn.ebp.ebp_zbjg.entity.EbpZbjg;
import com.gzxn.ebp.ebp_zbjg.service.IEbpZbjgService;
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
 * @Package: com.gzxn.ebp.ebp_ysd.controller
 * @ClassName: EbpYsdController
 * @Description: 验收单表-控制器
 * @author: CodeBird
 * @date: 2022-03-26 14:03:06
 */
@Api(tags = "验收单表")
@RestController
@RequestMapping("/ebp_ysd/ebpYsd")
public class EbpYsdController extends BaseController {

    @Autowired
    private IEbpYsdService iEbpYsdService;
    @Autowired
    private IEbpZbjgService iEbpZbjgService;

    @ApiOperation(value = "列表")
//    @RequiresPermissions("6000010001")
    @GetMapping("/list")
    public ResponseBean<Page> list(Page page,
                                   @ApiParam(value = "route_name") @RequestParam(name = "route_name", required = false) String route_name,
                                   @ApiParam(value = "route_path") @RequestParam(name = "route_path", required = false) String route_path,
                                   @ApiParam(value = "采购结果编号") @RequestParam(name = "jgbh", required = false) String jgbh,
                                   @ApiParam(value = "采购结果名称") @RequestParam(name = "xmmc", required = false) String xmmc) {
        QueryWrapper<EbpZbjg> zbjgQueryWrapper = new QueryWrapper<EbpZbjg>();
        QueryWrapper<EbpYsd> queryWrapper = new QueryWrapper<EbpYsd>();
        // 获取登录用户uid
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        if (StrUtil.isNotBlank(jgbh)) {
            zbjgQueryWrapper.like("jgbh", jgbh);
        }
        if (StrUtil.isNotBlank(xmmc)) {
            zbjgQueryWrapper.like("xmmc", xmmc);
        }
        Page listPage = new Page<>();
        // 查询-验收登记-待登记的数据
        if ("ebp_ysd_ddj".equals(route_path)) {
            zbjgQueryWrapper.eq("ebp_zbjg.cgjg", "1");
            zbjgQueryWrapper.isNull("ebp_ysd.zbjg_id");
            if (!"1".equals(uid)) {
                zbjgQueryWrapper.eq("ebp_zbjg.crer", uid);
            }
            listPage = iEbpZbjgService.ydjPageVo(page, zbjgQueryWrapper);
        }
        // 查询-验收登记-已登记的数据
        if ("ebp_ysd_ydj".equals(route_path)) {
            if (!"1".equals(uid)) {
                queryWrapper.eq("ebp_ysd.crer", uid);
            }
            listPage = iEbpYsdService.page(page, queryWrapper);
            List<EbpYsd> ysdList = listPage.getRecords();
            String zbjgid = "";
            for (int i=0; i<ysdList.size(); i++) {
                zbjgid = ysdList.get(i).getZbjgId();
                EbpZbjg ebpZbjg = iEbpZbjgService.getById(zbjgid);
                ysdList.get(i).setHwlx(ebpZbjg.getXmmc());
                ysdList.get(i).setLsclryhm(ebpZbjg.getJgbh());
            }
        }
        return ResponseBean.Success(listPage);
    }

    @ApiOperation(value = "添加")
//    @RequiresPermissions("6000010005")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody EbpYsd record) {
        if (iEbpYsdService.save(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "去编辑")
//    @RequiresPermissions("6000022001")
    @GetMapping("/toUpdate")
    public ResponseBean<Map<String, Object>> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        // 根据id查找招标结果
        EbpZbjg ebpZbjg = iEbpZbjgService.getById(id);
        // 根据zbjg_id查找验收单
        QueryWrapper<EbpYsd> ysdQueryWrapper = new QueryWrapper<EbpYsd>();
        ysdQueryWrapper.eq("zbjg_id", id);
        EbpYsd ebpYsd = iEbpYsdService.getOne(ysdQueryWrapper);
        // 封装数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("zbjgParameter", ebpZbjg);
        map.put("ysdParameter", ebpYsd);
        if (ObjectUtil.isNotNull(map)) {
            return ResponseBean.Success(map);
        } else {
            return ResponseBean.Fail();
        }
    }


    @ApiOperation(value = "编辑")
//    @RequiresPermissions("6000022001")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Map<String, Object> record) {
        if (iEbpYsdService.saveData(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "删除")
    @RequiresPermissions("6000010015")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iEbpYsdService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }
}
