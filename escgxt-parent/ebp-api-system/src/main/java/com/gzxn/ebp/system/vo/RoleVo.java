package com.gzxn.ebp.system.vo;

import com.gzxn.ebp.system.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.vo
 * @ClassName: RoleVo
 * @Author: CodeBird
 * @Date: 2022-03-06 18:06
 * @Description: 角色Vo
 */
@Data
public class RoleVo extends Role {

    private List<String> permissionIds;

}
