package com.gzxn.core.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName: BaseEntity
 * @Author: CodeBird
 * @Date: 2022-03-02 22:01
 * @Description: 公共字段实体类
 */
public abstract class BaseEntity<T extends Model> extends Model<BaseEntity<T>> {

    @ApiModelProperty(value = "id", required = true)
    @TableId("id")
    protected String id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "crtm", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected Date crtm;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "mdtm", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected Date mdtm;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField(value = "crer", fill = FieldFill.INSERT)
    protected String crer;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    @TableField(value = "mder", fill = FieldFill.INSERT_UPDATE)
    protected String mder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCrtm() {
        return crtm;
    }

    public void setCrtm(Date crtm) {
        this.crtm = crtm;
    }

    public Date getMdtm() {
        return mdtm;
    }

    public void setMdtm(Date mdtm) {
        this.mdtm = mdtm;
    }

    public String getCrer() {
        return crer;
    }

    public void setCrer(String crer) {
        this.crer = crer;
    }

    public String getMder() {
        return mder;
    }

    public void setMder(String mder) {
        this.mder = mder;
    }

}
