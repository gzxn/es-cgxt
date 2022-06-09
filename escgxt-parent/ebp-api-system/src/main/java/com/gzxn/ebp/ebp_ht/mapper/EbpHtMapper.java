package com.gzxn.ebp.ebp_ht.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.ebp.ebp_ht.entity.EbpHt;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Package: com.gzxn.ebp.ebp_ht.mapper
 * @ClassName: EbpHtMapper
 * @Description: 合同表-Mapper
 * @author: CodeBird
 * @date:  2022-03-26 14:02:50 
 */
public interface EbpHtMapper extends BaseMapper<EbpHt> {

    Page selectDngPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    Page selectWtjPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    Page selectYtjPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    Page selectWshPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

    Page selectYshPageVo(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
