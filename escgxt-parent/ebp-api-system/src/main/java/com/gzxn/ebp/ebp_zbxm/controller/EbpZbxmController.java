package com.gzxn.ebp.ebp_zbxm.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_cgb.entity.EbpCgb;
import com.gzxn.ebp.ebp_cgb.service.IEbpCgbService;
import com.gzxn.ebp.ebp_cgrw.entity.EbpCgrw;
import com.gzxn.ebp.ebp_cgrw.service.IEbpCgrwService;
import com.gzxn.ebp.ebp_zbxm.entity.EbpZbxm;
import com.gzxn.ebp.ebp_zbxm.service.IEbpZbxmService;
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
import java.util.Map;

/**
 * @Package: com.gzxn.ebp.ebp_zbxm.controller
 * @ClassName: EbpZbxmController
 * @Description: 招标项目表-控制器
 * @author: CodeBird
 * @date: 2022-03-26 14:01:37
 */
@Api(tags = "招标项目表")
@RestController
@RequestMapping("/ebp_zbxm/ebpZbxm")
public class EbpZbxmController extends BaseController {

    @Autowired
    private IEbpZbxmService iEbpZbxmService;
    @Autowired
    private IEbpCgrwService iEbpCgrwService;
    @Autowired
    private IEbpCgbService iEbpCgbService;

    @ApiOperation(value = "列表")
//    @RequiresPermissions("9000010001")
    @GetMapping("/list")
    public ResponseBean<Page> list(Page page,
                                   @ApiParam(value = "route_name") @RequestParam(name = "route_name", required = false) String route_name,
                                   @ApiParam(value = "route_path") @RequestParam(name = "route_path", required = false) String route_path,
                                   @ApiParam(value = "采购包编号") @RequestParam(name = "cgbbh", required = false) String cgbbh,
                                   @ApiParam(value = "采购包名称") @RequestParam(name = "cgbmc", required = false) String cgbmc,
                                   @ApiParam(value = "采购任务编号") @RequestParam(name = "cgrwbh", required = false) String cgrwbh,
                                   @ApiParam(value = "采购任务名称") @RequestParam(name = "cgrwmc", required = false) String cgrwmc) {
        QueryWrapper<EbpCgrw> cgrwQueryWrapper = new QueryWrapper<EbpCgrw>();
        QueryWrapper<EbpCgb> cgbQueryWrapper = new QueryWrapper<EbpCgb>();
        // 获取登录用户uid
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        if (StrUtil.isNotBlank(cgbbh)) {
            cgbQueryWrapper.like("cgbbh", cgbbh);
        }
        if (StrUtil.isNotBlank(cgbmc)) {
            cgbQueryWrapper.like("cgbmc", cgbmc);
        }
        if (StrUtil.isNotBlank(cgrwbh)) {
            cgrwQueryWrapper.like("cgrwbh", cgrwbh);
        }
        if (StrUtil.isNotBlank(cgrwmc)) {
            cgrwQueryWrapper.like("cgrwmc", cgrwmc);
        }
        Page listPage = new Page<>();
        // 查询-招标项目-未打包的数据
        if ("ebp_zbxm_wdb".equals(route_path)) {
//			cgrwQueryWrapper.notExists("SELECT * FROM ebp_cgrw WHERE cgsqid = cgsq.id");
            cgrwQueryWrapper.isNull("ebp_cgb.cgrwid");
            if (!"1".equals(uid)) {
                cgrwQueryWrapper.eq("ebp_cgsq.crer", uid);
            }
            listPage = iEbpCgrwService.yfpWdbPageVo(page, cgrwQueryWrapper);
        }
        // 查询-招标项目-已打包的数据
        if ("ebp_zbxm_ydb".equals(route_path)) {
            if (!"1".equals(uid)) {
                cgbQueryWrapper.eq("ebp_cgb.crer", uid);
            }
            listPage = iEbpCgbService.page(page, cgbQueryWrapper);
        }
        return ResponseBean.Success(listPage);
    }

    @ApiOperation(value = "添加")
//    @RequiresPermissions("9000021001")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody EbpZbxm record) {
        if (iEbpZbxmService.save(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "去编辑")
//    @RequiresPermissions("9000022001")
    @GetMapping("/toUpdate")
    public ResponseBean<Map<String, Object>> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
//        EbpZbxm ebpZbxm = iEbpZbxmService.getById(id);
        // 根据id查询采购任务
        EbpCgrw ebpCgrw = iEbpCgrwService.getById(id);
        // 根据cgrwid查找采购包
        QueryWrapper<EbpCgb> cgbQueryWrapper = new QueryWrapper<>();
        cgbQueryWrapper.eq("cgrwid", id);
        EbpCgb ebpCgb = iEbpCgbService.getOne(cgbQueryWrapper);
        // 封装数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("cgrwParameter", ebpCgrw);
        map.put("cgbParameter", ebpCgb);
        if (ObjectUtil.isNotNull(map)) {
            return ResponseBean.Success(map);
        } else {
            return ResponseBean.Fail();
        }
    }


    @ApiOperation(value = "编辑")
//    @RequiresPermissions("9000022001")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody EbpZbxm record) {
        if (iEbpZbxmService.updateById(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "采购任务打包")
//    @RequiresPermissions("9000022001")
    @PostMapping("/cgrwPack")
    public ResponseBean cgrwPack(@RequestBody Map<String, Object> record) {
        boolean flag = iEbpZbxmService.cgrwPack(record);
        if (flag) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "删除")
    @RequiresPermissions("9000023001")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iEbpZbxmService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }
}
