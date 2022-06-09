package com.gzxn.ebp.ebp_cgrw.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.ebp_cgrw.entity.EbpCgrw;

import java.util.Map;

/**
 * @Package: com.gzxn.ebp.ebp_cgrw.service
 * @ClassName: EbpCgrwService
 * @Description: 采购任务表-服务类接口
 * @author: CodeBird
 * @date: 2022-03-26 14:01:01
 */
public interface IEbpCgrwService extends IService<EbpCgrw> {

    /**
     * 保存数据
     */
    boolean saveData(Map<String, Object> record);

    /**
     * 获取已分配采购任务列表
     */
    Page yfpPageVo(Page page, Wrapper<EbpCgrw> wrapper);

    /**
     * 获取已分配采购任务未打包列表
     */
    Page yfpWdbPageVo(Page page, Wrapper<EbpCgrw> wrapper);

}
