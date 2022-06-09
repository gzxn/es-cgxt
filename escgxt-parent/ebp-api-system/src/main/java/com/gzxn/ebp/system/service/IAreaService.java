package com.gzxn.ebp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzxn.ebp.system.entity.Area;

import java.util.List;

/**
 * @Package: com.gzxn.ebp.system.service
 * @ClassName: IAreaService
 * @Author: CodeBird
 * @Date: 2022-03-06 18:10
 * @Description: 地区表-服务类接口
 */
public interface IAreaService extends IService<Area> {

    List<Area> list();

    List<Area> getByPid(String pid);

    /**
     * 根据数据表名和字段名，查询值
     *
     * @param tableName
     * @param columnName
     * @return
     */
    Object getColumnByField(String tableName, String columnName, String whereName, Object whereValue);
}