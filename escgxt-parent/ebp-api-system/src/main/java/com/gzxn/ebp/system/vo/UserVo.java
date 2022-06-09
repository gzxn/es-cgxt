package com.gzxn.ebp.system.vo;

import com.gzxn.ebp.system.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.system.vo
 * @ClassName: UserVo
 * @Author: CodeBird
 * @Date: 2022-03-06 18:07
 * @Description: 用户Vo
 */
@Data
@ApiModel(value = "用户Vo")
public class UserVo extends User {

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id", required = false)
    private String did;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id", required = false)
    private String rid;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称", required = false)
    private String dname;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", required = false)
    private String rname;

}
