package com.gzxn.ebp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.system.dto.RoleDto;
import com.gzxn.ebp.system.entity.Role;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.service
 * @ClassName: IRoleService
 * @Author: CodeBird
 * @Date: 2022-03-06 18:14
 * @Description: 角色表-服务类接口
 */
public interface IRoleService extends IService<Role> {

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
    public List<Role> listChildrenById(Serializable id);

    /**
     * 查找所有子节点（不包含自身）
     *
     * @param pid
     * @param parent
     * @return
     */
    public List<Role> listChildrenByPid(Serializable pid, List<Role> parent);

    /**
     * 根据uid查询角色
     *
     * @param uid
     * @return
     */
    List<Role> listByUid(String uid);

    /**
     * 保存RoleDto
     *
     * @param entity
     * @return
     */
    boolean saveDto(RoleDto entity);

    /**
     * 根据id修改RoleDto
     *
     * @param entity
     * @return
     */
    boolean updateDtoById(RoleDto entity);
}
