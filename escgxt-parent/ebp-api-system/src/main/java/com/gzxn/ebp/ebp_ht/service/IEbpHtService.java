package com.gzxn.ebp.ebp_ht.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.ebp_ht.entity.EbpHt;

import java.util.Map;

/**
 * 
 * @Package: com.gzxn.ebp.ebp_ht.service
 * @ClassName: EbpHtService
 * @Description:  合同表-服务类接口
 * @author: CodeBird
 * @date:  2022-03-26 14:02:50 
 */
public interface IEbpHtService extends IService<EbpHt> {

    /**
     * 获取合同待拟稿项目列表
     */
    Page dngPageVo(Page page, Wrapper wrapper);

    /**
     * 获取未提交合同项目列表
     */
    Page wtjPageVo(Page page, Wrapper wrapper);

    /**
     * 获取已提交合同项目列表
     */
    Page ytjPageVo(Page page, Wrapper wrapper);

    /**
     * 获取未审核合同项目列表
     */
    Page wshPageVo(Page page, Wrapper wrapper);

    /**
     * 获取已审核合同项目列表
     */
    Page yshPageVo(Page page, Wrapper wrapper);

    /**
     * 获取采购项目编号
     *
     * @return cgbh
     */
    String getHtLsh();

    /**
     * 保存数据
     */
    boolean saveData(Map<String, Object> record);

}
