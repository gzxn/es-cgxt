package com.gzxn.ebp.ebp_cgrw.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_cgrw.entity.EbpCgrw;
import com.gzxn.ebp.ebp_cgrw.service.IEbpCgrwService;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import com.gzxn.ebp.ebp_cgsq.service.IEbpCgsqService;
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
 * @Package: com.gzxn.ebp.ebp_cgrw.controller
 * @ClassName: EbpCgrwController
 * @Description: 采购任务表-控制器
 * @author: CodeBird
 * @date: 2022-03-26 14:01:00
 */
@Api(tags = "采购任务表")
@RestController
@RequestMapping("/ebp_cgrw/ebpCgrw")
public class EbpCgrwController extends BaseController {

    @Autowired
    private IEbpCgrwService iEbpCgrwService;
    @Autowired
    private IEbpCgsqService iEbpCgsqService;

    @ApiOperation(value = "列表")
//    @RequiresPermissions("2000010001")
    @GetMapping("/list")
    public ResponseBean<Page<EbpCgrw>> list(Page page,
                                            @ApiParam(value = "route_name") @RequestParam(name = "route_name", required = false) String route_name,
                                            @ApiParam(value = "route_path") @RequestParam(name = "route_path", required = false) String route_path,
                                            @ApiParam(value = "采购项目编号") @RequestParam(name = "cgbh", required = false) String cgbh,
                                            @ApiParam(value = "采购项目名称") @RequestParam(name = "xmmc", required = false) String xmmc,
                                            @ApiParam(value = "采购任务编号") @RequestParam(name = "cgrwbh", required = false) String cgrwbh,
                                            @ApiParam(value = "采购任务名称") @RequestParam(name = "cgrwmc", required = false) String cgrwmc) {
        QueryWrapper<EbpCgrw> queryCgrwWrapper = new QueryWrapper<EbpCgrw>();
        QueryWrapper<EbpCgsq> queryCgsqWrapper = new QueryWrapper<EbpCgsq>();
        // 获取登录用户uid
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        if (StrUtil.isNotBlank(cgbh)) {
            queryCgsqWrapper.like("cgbh", cgbh);
        }
        if (StrUtil.isNotBlank(xmmc)) {
            queryCgsqWrapper.like("xmmc", xmmc);
        }
        if (StrUtil.isNotBlank(cgrwbh)) {
            queryCgrwWrapper.like("cgrwbh", cgrwbh);
        }
        if (StrUtil.isNotBlank(cgrwmc)) {
            queryCgrwWrapper.like("cgrwmc", cgrwmc);
        }
        Page listPage = new Page<>();
        // 查询-采购申请-未分配的数据
        if ("ebp_cgrw_wfp".equals(route_path)) {
//            queryCgsqWrapper.eq("ebp_cgrw.cgsqid", null);
            if (!"1".equals(uid) && !"aeadd2ecd112c9ac96a916f880097e2f".equals(uid)) {
                queryCgsqWrapper.eq("cgsq.crer", uid);
            }
            queryCgsqWrapper.notExists("SELECT * FROM ebp_cgrw WHERE cgsqid = cgsq.id");
            listPage = iEbpCgsqService.yshtgPageVo(page, queryCgsqWrapper);
        }
        // 查询-采购任务-已分配的数据
        if ("ebp_cgrw_yfp".equals(route_path)) {
            if (!"1".equals(uid) && !"aeadd2ecd112c9ac96a916f880097e2f".equals(uid)) {
                queryCgrwWrapper.eq("ebp_cgsq.crer", uid);
            }
            listPage = iEbpCgrwService.yfpPageVo(page, queryCgrwWrapper);
        }
        return ResponseBean.Success(listPage);
    }

    @ApiOperation(value = "添加")
//    @RequiresPermissions("2000011001")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody EbpCgrw record) {
        if (iEbpCgrwService.save(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "去编辑")
//    @RequiresPermissions("2000012001")
    @GetMapping("/toUpdate")
    public ResponseBean<Map<String, Object>> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        // 采购申请数据
        EbpCgsq ebpCgsq = iEbpCgsqService.getById(id);
        // 查询采购任务数据
        QueryWrapper<EbpCgrw> cgrwQueryWrapper = new QueryWrapper<>();
        cgrwQueryWrapper.eq("cgsqid", id);
        EbpCgrw ebpCgrw = iEbpCgrwService.getOne(cgrwQueryWrapper);
        // 封装数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("cgsqParameter", ebpCgsq);
        map.put("cgrwParameter", ebpCgrw);
        if (ObjectUtil.isNotNull(map)) {
            return ResponseBean.Success(map);
        } else {
            return ResponseBean.Fail();
        }
    }


    @ApiOperation(value = "编辑")
//    @RequiresPermissions("2000012001")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Map<String, Object> record) {
        if (iEbpCgrwService.saveData(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "删除")
    @RequiresPermissions("2000013001")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iEbpCgrwService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

}
