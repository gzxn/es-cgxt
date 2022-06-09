package com.gzxn.ebp.monitor.vo;

import lombok.Data;

/**
 * @Package: com.gzxn.ebp.monitor.vo
 * @ClassName: DashboardVo
 * @Author: CodeBird
 * @Date: 2022-03-06 19:07
 * @Description: TODO
 */
@Data
public class DashboardVo {
    /**
     * 系统名称
     */
    private String systemName;
    /**
     * 系统位数
     */
    private String systemArch;

    /**
     * cpu名称
     */
    private String cpuName;
    /**
     * cpu用户使用量
     */
    private Long cpuUserUsed;
    /**
     * cpu系统使用量
     */
    private Long cpuSystemUsed;
    /**
     * cpu使用总量
     */
    private Long cpuUsed;
    /**
     * cpu空闲
     */
    private Long cpuIdle;
    /**
     * cpu总量
     */
    private Long cpuTotal;
    /**
     * cpu物理核数
     */
    private Integer cpuPhysicalCount;

    /**
     * cpu逻辑核数
     */
    private Integer cpuLogicalCount;

    /**
     * 内存总大小
     */
    private Long memoryTotal;

    /**
     * 内存使用量
     */
    private Long memoryUsed;


    /**
     * 内存空闲量
     */
    private Long memoryIdle;

    /**
     * jvmcpu使用核数
     */
    private Integer jvmCpuCount;

    /**
     * java版本
     */
    private String javaVersion;
    /**
     * jvm版本
     */
    private String jvmVersion;
    /**
     * java位数
     */
    private String javaArch;
    /**
     * jvm初始的总内存
     */
    private Long jvmMemoryInit;
    /**
     * jvm最大可用内存
     */
    private Long jvmMemoryTotal;
    /**
     * jvm已使用的内存
     */
    private Long jvmMemoryUsed;
    /**
     * jvm空闲量的内存
     */
    private Long jvmMemoryIdle;
}
