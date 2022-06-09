package com.gzxn.ebp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.system.entity.Department;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.service
 * @ClassName: IDepartmentService
 * @Author: CodeBird
 * @Date: 2022-03-06 18:11
 * @Description: 部门表-服务类接口
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 根据id删除子节点（包含自身）
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
    List<Department> listChildrenById(Serializable id);

    /**
     * 查找所有子节点（不包含自身）
     *
     * @param pid
     * @param parent
     * @return
     */
    List<Department> listChildrenByPid(Serializable pid, List<Department> parent);

    /**
     * 根据uid查询部门
     *
     * @param uid
     * @return
     */
    List<Department> listByUid(String uid);
}