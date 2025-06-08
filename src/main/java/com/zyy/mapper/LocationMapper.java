package com.zyy.mapper;

import com.zyy.entity.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LocationMapper {
    /**
     * 插入一个地点
     * @param location
     */
    void insert(Location location);

    /**
     * 根据id查询地点
     * @param locationId
     * @return
     */
    Location selectById(Integer locationId);
}
