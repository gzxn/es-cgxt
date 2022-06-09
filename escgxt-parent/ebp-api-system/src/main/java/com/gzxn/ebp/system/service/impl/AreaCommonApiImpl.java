package com.gzxn.ebp.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.gzxn.core.system.api.IAreaCommonApi;
import com.gzxn.core.system.dto.AreaDto;
import com.gzxn.ebp.system.entity.Area;
import com.gzxn.ebp.system.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Package: com.gzxn.ebp.system.service.impl
 * @ClassName: AreaCommonApiImpl
 * @Author: CodeBird
 * @Date: 2022-03-06 18:17
 * @Description: 地区公共Api
 */
@Service
public class AreaCommonApiImpl implements IAreaCommonApi {
    @Autowired
    private IAreaService areaService;

    @Override
    public List<AreaDto> getAllAreas() {
        return areaService.list().stream().map(area -> {
            return BeanUtil.copyProperties(area, AreaDto.class);
        }).collect(Collectors.toList());
    }

    @Override
    public AreaDto getAreaById(String id) {
        Area area = areaService.getById(id);
        AreaDto areaDto = Convert.convert(AreaDto.class, area);
        return areaDto;
    }

    @Override
    public Object getColumnByField(String tableName, String columnName, String whereName, Object whereValue) {
        return areaService.getColumnByField(tableName, columnName, whereName, whereValue);
    }
}
