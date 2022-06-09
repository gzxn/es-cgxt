package com.gzxn.ebp.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzxn.ebp.system.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.mapper
 * @ClassName: RoleMapper
 * @Author: CodeBird
 * @Date: 2022-03-06 18:03
 * @Description: 角色表-Mapper
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectByUid(@Param("uid") String uid);

}