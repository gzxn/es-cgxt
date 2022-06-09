package com.gzxn.ebp.ebp_zbjg.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.ebp.ebp_zbjg.entity.EbpZbjg;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @Package: com.gzxn.ebp.ebp_zbjg.mapper
 * @ClassName: EbpZbjgMapper
 * @Description: 招标结果表-Mapper
 * @author: CodeBird
 * @date:  2022-03-26 14:02:01 
 */
public interface EbpZbjgMapper extends BaseMapper<EbpZbjg> {

    Page selectYdjPageVo(Page page, @Param(Constants.WRAPPER) Wrapper<EbpZbjg> wrapper);

}
