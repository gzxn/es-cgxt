package com.gzxn.ebp.system.dto;

import com.gzxn.ebp.system.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Package: com.gzxn.ebp.system.dto
 * @ClassName: UserDto
 * @Author: CodeBird
 * @Date: 2022-03-06 17:58
 * @Description: 用户表-实体类
 */
@ApiModel(value = "用户表")
@Data
public class UserDto extends User {

    private String roleId;

    private String newPwd;

}

