package com.gzxn.ebp.monitor.controller;

import com.gzxn.core.base.controller.BaseController;
import com.gzxn.core.vo.ResponseBean;
import com.gzxn.ebp.monitor.vo.DashboardVo;
import com.gzxn.ebp.system.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.HardwareAbstractionLayer;


/**
 * @Package: com.gzxn.ebp.monitor.controller
 * @ClassName: DashboardController
 * @Author: CodeBird
 * @Date: 2022-03-06 19:07
 * @Description: 服务器面板
 */
@Api(tags = "服务器面板")
@RestController
@RequestMapping("/monitor/dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private IDepartmentService iDepartmentService;

    @ApiOperation(value = "服务器面板信息")
    @RequiresPermissions(value = { "00050005" })
    @GetMapping("/getInfo")
    public ResponseBean<DashboardVo> getInfo() {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        long[] prevTicks = hardwareAbstractionLayer.getProcessor().getSystemCpuLoadTicks();
        long[] ticks = hardwareAbstractionLayer.getProcessor().getSystemCpuLoadTicks();
        DashboardVo dashboardVo = new DashboardVo();
        dashboardVo.setSystemName(systemInfo.getOperatingSystem().toString());
        dashboardVo.setSystemArch(System.getProperty("os.arch"));
        dashboardVo.setCpuUserUsed(ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()]);
        dashboardVo.setCpuSystemUsed(ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()]);
        dashboardVo.setCpuUsed(dashboardVo.getCpuSystemUsed() + dashboardVo.getCpuUserUsed());
        dashboardVo.setCpuIdle(ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()]);
        dashboardVo.setCpuTotal(dashboardVo.getCpuUsed() + dashboardVo.getCpuIdle());
        dashboardVo.setCpuName(hardwareAbstractionLayer.getProcessor().toString());
        dashboardVo.setCpuPhysicalCount(hardwareAbstractionLayer.getProcessor().getPhysicalProcessorCount());
        dashboardVo.setCpuLogicalCount(hardwareAbstractionLayer.getProcessor().getLogicalProcessorCount());
        dashboardVo.setJavaVersion(System.getProperty("java.version"));
        dashboardVo.setJavaArch(System.getProperty("sun.arch.data.model"));
        dashboardVo.setJvmCpuCount(Runtime.getRuntime().availableProcessors());
        dashboardVo.setJvmVersion(System.getProperty("java.vm.version"));
        dashboardVo.setJvmMemoryInit(Runtime.getRuntime().totalMemory());
        dashboardVo.setJvmMemoryTotal(Runtime.getRuntime().maxMemory());
        dashboardVo.setJvmMemoryIdle(Runtime.getRuntime().freeMemory());
        dashboardVo.setJvmMemoryUsed((dashboardVo.getJvmMemoryInit() - dashboardVo.getJvmMemoryIdle()));
        dashboardVo.setMemoryTotal(systemInfo.getHardware().getMemory().getTotal());
        dashboardVo.setMemoryIdle(systemInfo.getHardware().getMemory().getAvailable());
        dashboardVo.setMemoryUsed((dashboardVo.getMemoryTotal() - dashboardVo.getMemoryIdle()));
        return ResponseBean.Success(dashboardVo);
    }

}
