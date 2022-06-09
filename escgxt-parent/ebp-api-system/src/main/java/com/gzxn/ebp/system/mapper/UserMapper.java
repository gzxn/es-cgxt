package com.gzxn.ebp.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.ebp.system.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Package: com.gzxn.ebp.system.mapper
 * @ClassName: UserMapper
 * @Author: CodeBird
 * @Date: 2022-03-06 18:03
 * @Description: 用户表-Mapper
 */
public interface UserMapper extends BaseMapper<User> {

    Page selectPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
