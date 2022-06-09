package com.gzxn.ebp.system.vo;

import com.gzxn.ebp.system.entity.User;
import com.gzxn.ebp.system.entity.UserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.system.vo
 * @ClassName: UserToUpdateVo
 * @Author: CodeBird
 * @Date: 2022-03-06 18:07
 * @Description: 用户去编辑Vo
 */
@Data
@ApiModel(value = "用户去编辑Vo")
public class UserToUpdateVo {

    @ApiModelProperty(value = "用户内容")
    private User user;

    @ApiModelProperty(value = "用户角色")
    private UserRole userRole;

}
