package com.gzxn.ebp.ebp_cgb.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.ebp_cgb.entity.EbpCgb;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsqQd;

import java.util.List;

/**
 * 
 * @Package: com.gzxn.ebp.ebp_cgb.service
 * @ClassName: EbpCgbService
 * @Description:  采购包表-服务类接口
 * @author: CodeBird
 * @date:  2022-03-26 13:59:18 
 */
public interface IEbpCgbService extends IService<EbpCgb> {

    /**
     * 获取已分配采购任务未打包列表
     */
    Page ydbPageVo(Page page, Wrapper<EbpCgb> wrapper);

    /**
     * 根据cgbid查找采购申请
     */
    EbpCgsq selectCgsqByCgbid(String id);

    /**
     * 根据cgbid查找采购申请清单
     */
    List<EbpCgsqQd> selectCgsqqdByCgbid(String id);

}
