package com.gzxn.core.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.gzxn.core.system.dto
 * @ClassName: DictionaryDto
 * @Author: CodeBird
 * @Date: 2022-03-06 12:04
 * @Description:
 */
@ApiModel(value = "数据字典")
@Data
public class DictionaryDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private String id;
    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称", required = true)
    private String name;
    /**
     * 字典编号
     */
    @ApiModelProperty(value = "字典编号", required = true)
    private String dkey;
    /**
     * 属性值
     */
    @ApiModelProperty(value = "属性值")
    private String dvalue;
    /**
     * 类型：1-选项、2-属性值、3-系统属性
     */
    @ApiModelProperty(value = "类型：1-选项、2-属性值、3-系统属性", required = true)
    private Integer type;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
    /**
     * 是否禁用：1-是、2-否
     */
    @ApiModelProperty(value = "是否禁用：1-是、2-否", required = true)
    private Integer isdisable;

    /**
     * 字典子项
     */
    @ApiModelProperty(value = "字典子项")
    List<DictionarydataDto> options;
}
