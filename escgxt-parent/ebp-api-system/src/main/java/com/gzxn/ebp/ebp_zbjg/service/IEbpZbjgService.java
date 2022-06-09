package com.gzxn.ebp.ebp_zbjg.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.ebp_zbjg.entity.EbpZbjg;

import java.util.Map;

/**
 * @Package: com.gzxn.ebp.ebp_zbjg.service
 * @ClassName: EbpZbjgService
 * @Description: 招标结果表-服务类接口
 * @author: CodeBird
 * @date: 2022-03-26 14:02:02
 */
public interface IEbpZbjgService extends IService<EbpZbjg> {

    /**
     * 获取已登记采购结果列表
     */
    Page ydjPageVo(Page page, Wrapper<EbpZbjg> wrapper);

    /**
     * 保存数据
     */
    boolean saveData(Map<String, Object> record);

}
