package com.gzxn.ebp.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gzxn.ebp.system.entity.Area;
import org.apache.ibatis.annotations.Param;

/**
 * @Package: com.gzxn.ebp.system.mapper
 * @ClassName: AreaMapper
 * @Author: CodeBird
 * @Date: 2022-03-06 17:59
 * @Description: 地区表-Mapper
 */
public interface AreaMapper extends BaseMapper<Area> {

    Object getColumnByField(@Param("tableName") String tableName, @Param("columnName") String columnName,
                            @Param("whereName") String whereName, @Param("whereValue") Object whereValue);


}
