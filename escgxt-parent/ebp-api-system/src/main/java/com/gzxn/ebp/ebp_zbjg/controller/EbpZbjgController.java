package com.gzxn.ebp.ebp_zbjg.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.constant.Constant;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.ebp_cgb.entity.EbpCgb;
import com.gzxn.ebp.ebp_cgb.service.IEbpCgbService;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsqQd;
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
 * @Package: com.gzxn.ebp.ebp_zbjg.controller
 * @ClassName: EbpZbjgController
 * @Description: 招标结果表-控制器
 * @author: CodeBird
 * @date: 2022-03-26 14:02:01
 */
@Api(tags = "招标结果表")
@RestController
@RequestMapping("/ebp_zbjg/ebpZbjg")
public class EbpZbjgController extends BaseController {

    @Autowired
    private IEbpZbjgService iEbpZbjgService;
    @Autowired
    private IEbpCgbService iEbpCgbService;

    @ApiOperation(value = "列表")
//    @RequiresPermissions("8000010001")
    @GetMapping("/list")
    public ResponseBean<Page> list(Page page,
                                   @ApiParam(value = "route_name") @RequestParam(name = "route_name", required = false) String route_name,
                                   @ApiParam(value = "route_path") @RequestParam(name = "route_path", required = false) String route_path,
                                   @ApiParam(value = "采购包编号") @RequestParam(name = "cgbbh", required = false) String cgbbh,
                                   @ApiParam(value = "采购包名称") @RequestParam(name = "cgbmc", required = false) String cgbmc,
                                   @ApiParam(value = "采购结果编号") @RequestParam(name = "jgbh", required = false) String jgbh,
                                   @ApiParam(value = "采购结果名称") @RequestParam(name = "xmmc", required = false) String xmmc) {
        QueryWrapper<EbpCgb> cgbQueryWrapper = new QueryWrapper<EbpCgb>();
        QueryWrapper<EbpZbjg> zbjgQueryWrapper = new QueryWrapper<EbpZbjg>();
        // 获取登录用户uid
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uid = (String) request.getAttribute(Constant.REQUEST_ATTRIBUTE_UID);
        if (StrUtil.isNotBlank(cgbbh)) {
            cgbQueryWrapper.like("cgbbh", cgbbh);
        }
        if (StrUtil.isNotBlank(cgbmc)) {
            cgbQueryWrapper.like("cgbmc", cgbmc);
        }
        if (StrUtil.isNotBlank(jgbh)) {
            zbjgQueryWrapper.like("jgbh", jgbh);
        }
        if (StrUtil.isNotBlank(xmmc)) {
            zbjgQueryWrapper.like("xmmc", xmmc);
        }
        Page listPage = new Page<>();
        // 查询-采购结果登记-未登记的数据
        if ("ebp_zbjg_wdj".equals(route_path)) {
//			cgrwQueryWrapper.notExists("SELECT * FROM ebp_cgrw WHERE cgsqid = cgsq.id");
            cgbQueryWrapper.isNull("ebp_zbjg.cgbid");
            if (!"1".equals(uid)) {
                cgbQueryWrapper.eq("ebp_cgb.crer", uid);
            }
            listPage = iEbpCgbService.ydbPageVo(page, cgbQueryWrapper);
        }
        // 查询-采购结果登记-已登记的数据
        if ("ebp_zbjg_ydj".equals(route_path)) {
            if (!"1".equals(uid) && !"ce5a9afa75505ac4d827eb6c6dfc7ffb".equals(uid)) {
                zbjgQueryWrapper.eq("ebp_zbjg.crer", uid);
            }
            listPage = iEbpZbjgService.page(page, zbjgQueryWrapper);
        }
        return ResponseBean.Success(listPage);
    }

    @ApiOperation(value = "添加")
//    @RequiresPermissions("8000010005")
    @PostMapping("/save")
    public ResponseBean save(@RequestBody EbpZbjg record) {
        if (iEbpZbjgService.save(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "去编辑")
//    @RequiresPermissions("8000022001")
    @GetMapping("/toUpdate")
    public ResponseBean<Map<String, Object>> toUpdate(@ApiParam(value = "id") @RequestParam(name = "id") String id) {
        // 根据id查询采购包
        EbpCgb ebpCgb = iEbpCgbService.getById(id);
        // 根据cgbid查找采购申请
        EbpCgsq ebpCgsq = iEbpCgbService.selectCgsqByCgbid(id);
        // 根据cgbid查找采购申请清单
        List<EbpCgsqQd> cgsqQdList = iEbpCgbService.selectCgsqqdByCgbid(id);
        // 根据cgbid查找招标结果
        QueryWrapper<EbpZbjg> zbjgQueryWrapper = new QueryWrapper<EbpZbjg>();
        zbjgQueryWrapper.eq("cgbid", id);
        EbpZbjg ebpZbjg = iEbpZbjgService.getOne(zbjgQueryWrapper);
        // 封装数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("cgsqParameter", ebpCgsq);
        map.put("cgsqqdParameter", cgsqQdList);
        map.put("cgbParameter", ebpCgb);
        map.put("zbjgParameter", ebpZbjg);
        if (ObjectUtil.isNotNull(map)) {
            return ResponseBean.Success(map);
        } else {
            return ResponseBean.Fail();
        }
    }


    @ApiOperation(value = "编辑")
//    @RequiresPermissions("8000022001")
    @PutMapping("/update")
    public ResponseBean update(@RequestBody Map<String, Object> record) {
        if (iEbpZbjgService.saveData(record)) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }

    @ApiOperation(value = "删除")
    @RequiresPermissions("8000010015")
    @DeleteMapping("/remove")
    public ResponseBean remove(@ApiParam(value = "ids") @RequestParam(name = "ids") String[] ids) {
        if (iEbpZbjgService.removeByIds(Arrays.asList(ids))) {
            return ResponseBean.Success();
        } else {
            return ResponseBean.Fail();
        }
    }
}
