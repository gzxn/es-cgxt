package com.gzxn.ebp.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzxn.ebp.system.dto.UserDto;
import com.gzxn.ebp.system.entity.User;
import com.gzxn.ebp.system.entity.UserRole;
import com.gzxn.ebp.system.mapper.UserMapper;
import com.gzxn.ebp.system.service.IUserRoleService;
import com.gzxn.ebp.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: UserServiceImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:26
 * @Description: 用户表-服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private IUserRoleService iUserRoleService;

    @Override
    public boolean saveDto(UserDto dto) {
        boolean result = save(dto);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", dto.getId());
        iUserRoleService.remove(queryWrapper);
        UserRole entity = new UserRole();
        entity.setUid(dto.getId());
        entity.setRid(dto.getRoleId());
        iUserRoleService.save(entity);
        return result;
    }

    @Override
    public boolean updateDtoById(UserDto dto) {
        boolean result = updateById(dto);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid", dto.getId());
        iUserRoleService.remove(queryWrapper);
        UserRole entity = new UserRole();
        entity.setUid(dto.getId());
        entity.setRid(dto.getRoleId());
        iUserRoleService.save(entity);
        return result;
    }

    @Override
    public Page pageVo(Page page, Wrapper wrapper) {
        return this.baseMapper.selectPageVo(page, wrapper);
    }

}