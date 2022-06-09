package com.gzxn.ebp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.system.entity.Permission;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.service
 * @ClassName: IPermissionService
 * @Author: CodeBird
 * @Date: 2022-03-06 18:13
 * @Description: 权限表-服务类接口
 */
public interface IPermissionService extends IService<Permission> {

    /**
     * 根据id删除子节点
     *
     * @param id
     * @return
     */
    boolean removeChildrenById(Serializable id);

    /**
     * 根据ids删除子节点（包含自身）
     *
     * @param idList
     * @return
     */
    boolean removeChildrenByIds(Collection<? extends Serializable> idList);

    /**
     * 查找所有子节点（包含自身）
     *
     * @param id
     * @return
     */
    public List<Permission> listChildrenById(Serializable id);

    /**
     * 查找所有子节点（不包含自身）
     *
     * @param pid
     * @param parent
     * @return
     */
    public List<Permission> listChildrenByPid(Serializable pid, List<Permission> parent);

    /**
     * 根据用户id查找权限
     *
     * @param uid
     * @return
     */
    List<Permission> listByUid(String uid);
}
