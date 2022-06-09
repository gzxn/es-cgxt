package com.gzxn.ebp.ebp_ysd.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.ebp.ebp_ysd.entity.EbpYsd;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Package: com.gzxn.ebp.ebp_ysd.mapper
 * @ClassName: EbpYsdMapper
 * @Description: 验收单表-Mapper
 * @author: CodeBird
 * @date:  2022-03-26 14:03:06 
 */
public interface EbpYsdMapper extends BaseMapper<EbpYsd> {

    Page selectYsdPageVo(Page page, @Param(Constants.WRAPPER) Wrapper<EbpYsd> wrapper);

}
