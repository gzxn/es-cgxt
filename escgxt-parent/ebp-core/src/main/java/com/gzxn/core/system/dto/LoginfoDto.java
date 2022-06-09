package com.gzxn.core.system.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Package: com.gzxn.core.system.dto
 * @ClassName: LoginfoDto
 * @Author: CodeBird
 * @Date: 2022-03-06 11:33
 * @Description: 日志信息
 */
@ApiModel(value = "日志信息")
@Data
public class LoginfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("id")
    private String id;
    /**
     * 用户id
     */
    @TableField("uid")
    private String uid;
    /**
     * 登录ip
     */
    @TableField("ip")
    private String ip;
    /**
     * 客户端
     */
    @TableField("useragent")
    private String useragent;
    /**
     * 模块
     */
    @TableField("module")
    private String module;
    /**
     * 操作
     */
    @TableField("operation")
    private String operation;
    /**
     * 操作时间
     */
    @TableField("optime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date optime;
    /**
     * 日志类型 1-业务、2-错误信息
     */
    @TableField("type")
    private Integer type;
    /**
     * 操作接口
     */
    @TableField("uri")
    private String uri;
    /**
     * 内容
     */
    @TableField("content")
    private String content;

}
