package com.gzxn.ebp.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.gzxn.ebp.system.entity.Permission;
import com.gzxn.ebp.system.mapper.PermissionMapper;
import com.gzxn.ebp.system.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: PermissionServiceImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:22
 * @Description: 权限表-服务实现类
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public boolean removeChildrenById(Serializable id) {
        List<Permission> permissions = listChildrenById(id);
        List<String> ids = permissions.stream().map((t) -> {
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
    public List<Permission> listChildrenById(Serializable id) {
        List<Permission> permissions = null;
        Permission permission = baseMapper.selectById(id);
        if (ObjectUtil.isNotEmpty(permission)) {
            permissions = new ArrayList();
            permissions.add(permission);
            listChildrenByPid(permission.getId(), permissions);
        }
        return permissions;
    }

    @Override
    public List<Permission> listChildrenByPid(Serializable pid, List<Permission> parent) {
        if (ObjectUtil.isEmpty(parent)) {
            parent = new ArrayList<Permission>();
        }
        QueryWrapper<Permission> queryWrapper = new QueryWrapper();
        List<Permission> children = baseMapper.selectList(queryWrapper.eq("pid", pid));
        if (ObjectUtil.isNotEmpty(children)) {
            parent.addAll(children);
            for (Permission permission : children) {
                listChildrenByPid(permission.getId(), parent);
            }
        }

        return parent;
    }

    @Override
    public List<Permission> listByUid(String uid) {
        return this.baseMapper.selectByUid(uid);
    }

}
