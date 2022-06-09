package com.gzxn.ebp.ebp_zbxm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.ebp_zbxm.entity.EbpZbxm;

import java.util.Map;

/**
 *
 * @Package: com.gzxn.ebp.ebp_zbxm.service
 * @ClassName: EbpZbxmService
 * @Description: 招标项目表-服务类接口
 * @author: CodeBird
 * @date: 2022-03-26 14:01:37
 */
public interface IEbpZbxmService extends IService<EbpZbxm> {

    /**
     * 采购任务打包
     */
    boolean cgrwPack(Map<String, Object> record);

}
