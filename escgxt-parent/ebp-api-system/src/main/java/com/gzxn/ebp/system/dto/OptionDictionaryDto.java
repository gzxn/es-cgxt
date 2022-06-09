package com.gzxn.ebp.system.dto;

import com.gzxn.ebp.system.entity.Dictionarydata;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.dto
 * @ClassName: OptionDictionaryDto
 * @Author: CodeBird
 * @Date: 2022-03-06 17:56
 * @Description: 选项字典Dto
 */
@ApiModel(value = "选项字典Dto")
@Data
public class OptionDictionaryDto implements Serializable {

    @ApiModelProperty(value = "字典id", required = true)
    private String did;
    @ApiModelProperty(value = "选项数据")
    private List<Dictionarydata> dictionarydatas;

}