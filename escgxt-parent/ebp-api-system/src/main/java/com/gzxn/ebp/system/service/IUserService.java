package com.gzxn.ebp.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.system.dto.UserDto;
import com.gzxn.ebp.system.entity.User;

/**
 * @Package: com.gzxn.ebp.system.service
 * @ClassName: IUserService
 * @Author: CodeBird
 * @Date: 2022-03-06 18:16
 * @Description: 用户表-服务类接口
 */
public interface IUserService extends IService<User> {

    boolean saveDto(UserDto dto);

    boolean updateDtoById(UserDto dto);

    Page pageVo(Page page, Wrapper wrapper);

}
