package com.gzxn.ebp.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.gzxn.ebp.system.dto.RoleDto;
import com.gzxn.ebp.system.entity.Role;
import com.gzxn.ebp.system.entity.RoleDepartment;
import com.gzxn.ebp.system.entity.RolePermission;
import com.gzxn.ebp.system.mapper.RoleMapper;
import com.gzxn.ebp.system.service.IRoleDepartmentService;
import com.gzxn.ebp.system.service.IRolePermissionService;
import com.gzxn.ebp.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: RoleServiceImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:24
 * @Description:
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private IRolePermissionService iRolePermissionService;
    @Autowired
    private IRoleDepartmentService iRoleDepartmentService;

    @Override
    public boolean saveDto(RoleDto entity) {
        save(entity);
        updateRoleDepartment(entity.getId(), entity.getDepartmentId());
        updateRolePermission(entity.getId(), entity.getPermissionIds());
        return true;
    }

    @Override
    public boolean updateDtoById(RoleDto entity) {
        updateById(entity);
        updateRoleDepartment(entity.getId(), entity.getDepartmentId());
        updateRolePermission(entity.getId(), entity.getPermissionIds());
        return true;
    }

    private void updateRoleDepartment(String rid, String departmentId) {
        // 先删除数据
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid", rid);
        iRoleDepartmentService.remove(queryWrapper);
        // 新增数据
        RoleDepartment roleDepartment = new RoleDepartment();
        roleDepartment.setDid(departmentId);
        roleDepartment.setRid(rid);
        iRoleDepartmentService.save(roleDepartment);
    }

    private void updateRolePermission(String rid, List<String> permissionIds) {
        // 先删除数据
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("rid", rid);
        iRolePermissionService.remove(queryWrapper);
        // 新增数据
        List<RolePermission> rolePermissions = new ArrayList<RolePermission>();
        for (String permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPid(permissionId);
            rolePermission.setRid(rid);
            rolePermissions.add(rolePermission);
        }
        iRolePermissionService.saveBatch(rolePermissions);
    }

    @Override
    public boolean removeChildrenById(Serializable id) {
        List<Role> roles = listChildrenById(id);
        List<String> ids = roles.stream().map((t) -> {
            return t.getId();
        }).collect(Collectors.toList());
        return SqlHelper.retBool(baseMapper.deleteBatchIds(ids));
    }

    @Override
    public boolean removeChildrenByIds(Collection<? extends Serializable> idList) {
        if (ObjectUtil.isNotEmpty(idList)) {
            for (Serializable id : idList) {
                if (!this.removeById(id)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Role> listChildrenById(Serializable id) {
        List<Role> role = null;
        Role prole = baseMapper.selectById(id);
        if (ObjectUtil.isNotEmpty(prole)) {
            role = new ArrayList();
            role.add(prole);
            listChildrenByPid(prole.getId(), role);

        }
        return role;
    }

    @Override
    public List<Role> listChildrenByPid(Serializable pid, List<Role> parent) {
        if (ObjectUtil.isEmpty(parent)) {
            parent = new ArrayList<Role>();
        }
        QueryWrapper<Role> queryWrapper = new QueryWrapper();
        List<Role> children = baseMapper.selectList(queryWrapper.eq("pid", pid));
        if (ObjectUtil.isNotEmpty(children)) {
            parent.addAll(children);
            for (Role prole : children) {
                listChildrenByPid(prole.getId(), parent);
            }
        }

        return parent;
    }

    @Override
    public List<Role> listByUid(String uid) {
        return this.baseMapper.selectByUid(uid);
    }

}
