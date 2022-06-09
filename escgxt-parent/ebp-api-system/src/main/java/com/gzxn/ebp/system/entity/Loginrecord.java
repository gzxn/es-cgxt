package com.gzxn.ebp.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gzxn.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Package: com.gzxn.ebp.system.entity
 * @ClassName: Loginrecord
 * @Author: CodeBird
 * @Date: 2022-03-06 17:51
 * @Description: 登录记录-实体类
 */
@ApiModel(value = "登录记录")
@Data
public class Loginrecord extends BaseEntity<Loginrecord> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id", required = true)
    @TableId("id")
    private String id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableField("uid")
    private String uid;
    /**
     * 登录ip
     */
    @ApiModelProperty(value = "登录ip")
    @TableField("ip")
    private String ip;
    /**
     * 客户端
     */
    @ApiModelProperty(value = "客户端")
    @TableField("useragent")
    private String useragent;
    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField("logintime")
    private Date logintime;
    /**
     * 登录结果 1-成功、2-失败
     */
    @ApiModelProperty(value = "登录结果 1-成功、2-失败")
    @TableField("loginresult")
    private Integer loginresult;

}
