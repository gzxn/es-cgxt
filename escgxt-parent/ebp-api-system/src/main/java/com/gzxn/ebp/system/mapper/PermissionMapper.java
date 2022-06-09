package com.gzxn.ebp.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzxn.ebp.system.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.mapper
 * @ClassName: PermissionMapper
 * @Author: CodeBird
 * @Date: 2022-03-06 18:02
 * @Description: 权限表-Mapper
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> selectByUid(@Param("uid") String uid);

}
