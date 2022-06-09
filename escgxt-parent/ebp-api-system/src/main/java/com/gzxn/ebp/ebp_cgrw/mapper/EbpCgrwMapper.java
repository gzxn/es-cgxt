package com.gzxn.ebp.ebp_cgrw.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.ebp.ebp_cgrw.entity.EbpCgrw;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Package: com.gzxn.ebp.ebp_cgrw.mapper
 * @ClassName: EbpCgrwMapper
 * @Description: 采购任务表-Mapper
 * @author: CodeBird
 * @date:  2022-03-26 14:01:01 
 */
public interface EbpCgrwMapper extends BaseMapper<EbpCgrw> {

    Page selectYfpPageVo(Page page, @Param(Constants.WRAPPER) Wrapper<EbpCgrw> wrapper);

    Page selectYfpWdbPageVo(Page page, @Param(Constants.WRAPPER) Wrapper<EbpCgrw> wrapper);


}
