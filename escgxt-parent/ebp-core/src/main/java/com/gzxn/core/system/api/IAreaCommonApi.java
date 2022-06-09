package com.gzxn.core.system.api;

import com.gzxn.core.system.dto.AreaDto;

import java.util.List;

/**
 * @Package: com.gzxn.core.system.api
 * @ClassName: IAreaCommonApi
 * @Author: CodeBird
 * @Date: 2022-03-06 16:59
 * @Description: 地区公共业务接口
 */
public interface IAreaCommonApi {

    List<AreaDto> getAllAreas();

    AreaDto getAreaById(String id);

    Object getColumnByField(String tableName, String columnName, String whereName, Object whereValue);

}
