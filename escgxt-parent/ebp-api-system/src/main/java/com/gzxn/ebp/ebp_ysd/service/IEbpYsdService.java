package com.gzxn.ebp.ebp_ysd.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.ebp_ysd.entity.EbpYsd;

import java.util.Map;

/**
 * @Package: com.gzxn.ebp.ebp_ysd.service
 * @ClassName: EbpYsdService
 * @Description: 验收单表-服务类接口
 * @author: CodeBird
 * @date: 2022-03-26 14:03:06
 */
public interface IEbpYsdService extends IService<EbpYsd> {

    /**
     * 获取已登记验收列表
     */
    Page ysdPageVo(Page page, Wrapper<EbpYsd> wrapper);

    /**
     * 保存数据
     */
    boolean saveData(Map<String, Object> record);

}
