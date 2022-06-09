package com.gzxn.ebp.ebp_cgsq.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.ebp_cgsq.entity.EbpCgsq;

import java.util.Map;

/**
 * @Package: com.gzxn.ebp.ebp_cgsq.service
 * @ClassName: EbpCgsqService
 * @Description: 采购申请表-服务类接口
 * @author: CodeBird
 * @date: 2022-03-26 13:57:47
 */
public interface IEbpCgsqService extends IService<EbpCgsq> {

    Page pageVo(Page page, Wrapper wrapper);

    /**
     * 获取未提交采购项目列表
     */
    Page wtjPageVo(Page page, Wrapper wrapper);

    /**
     * 获取已提交采购项目列表
     */
    Page ytjPageVo(Page page, Wrapper wrapper);

    /**
     * 获取未审核采购项目列表
     */
    Page wshPageVo(Page page, Wrapper wrapper);

    /**
     * 获取已审核采购项目列表
     */
    Page yshPageVo(Page page, Wrapper wrapper);

    /**
     * 获取审批通过的采购项目列表
     */
    Page yshtgPageVo(Page page, Wrapper wrapper);

    /**
     * 获取采购项目编号
     *
     * @return cgbh
     */
    String getCgsqLsh();

    /**
     * 获取采购项目清单编号
     *
     * @return hwbh
     */
    String getCgsqqdLsh(String cgbh);

    /**
     * 保存数据
     */
    boolean saveData(Map<String, Object> record);
}
