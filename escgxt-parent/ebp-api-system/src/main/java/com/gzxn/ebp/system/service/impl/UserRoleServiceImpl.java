package com.gzxn.ebp.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.system.entity.UserRole;
import com.gzxn.ebp.system.mapper.UserRoleMapper;
import com.gzxn.ebp.system.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: UserRoleServiceImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:26
 * @Description: 用户角色表-服务实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
