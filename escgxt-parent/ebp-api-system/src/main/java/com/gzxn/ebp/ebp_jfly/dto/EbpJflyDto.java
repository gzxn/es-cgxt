package com.gzxn.ebp.ebp_jfly.dto;

import com.gzxn.ebp.ebp_jfly.entity.EbpJfly;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.ebp_jfly.vo
 * @ClassName: EbpJflyVo
 * @Author: CodeBird
 * @Date: 2022-04-21 20:28
 * @Description: 经费部门表-实体类
 */
@Data
@ApiModel(value = "经费部门表")
public class EbpJflyDto extends EbpJfly {
    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id", required = false)
    private String did;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称", required = false)
    private String dname;
    /**
     * 部门编码
     */
    @ApiModelProperty(value = "部门编码", required = false)
    private String dcode;
}
