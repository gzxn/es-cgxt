package com.gzxn.ebp.ebp_cgb.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzxn.ebp.ebp_cgb.entity.EbpCgb;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsqQd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @Package: com.gzxn.ebp.ebp_cgb.mapper
 * @ClassName: EbpCgbMapper
 * @Description: 采购包表-Mapper
 * @author: CodeBird
 * @date:  2022-03-26 13:59:18 
 */
public interface EbpCgbMapper extends BaseMapper<EbpCgb> {

    Page selectYdbPageVo(Page page, @Param(Constants.WRAPPER) Wrapper<EbpCgb> wrapper);

    EbpCgsq selectCgsqByCgbid(@Param("id") String id);

    List<EbpCgsqQd> selectCgsqqdByCgbid(@Param("id") String id);

}
