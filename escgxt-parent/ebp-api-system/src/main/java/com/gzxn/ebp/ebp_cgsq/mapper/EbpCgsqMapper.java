package com.gzxn.ebp.ebp_cgsq.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import org.apache.ibatis.annotations.Param;

/**
 * Copyright  2022-03-26 QLH. Tech Ltd. All rights reserved.
 * 
 * @Package: com.gzxn.ebp.ebp_cgsq.mapper
 * @ClassName: EbpCgsqMapper
 * @Description: 采购申请表-Mapper
 * @author: CodeBird
 * @date:  2022-03-26 13:57:47 
 */
public interface EbpCgsqMapper extends BaseMapper<EbpCgsq> {

    Page selectPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    Page selectWtjPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    Page selectYtjPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    Page selectWshPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    Page selectYshPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    Page selectYshtgPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
