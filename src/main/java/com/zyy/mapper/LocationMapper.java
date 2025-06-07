package com.zyy.mapper;

import com.zyy.entity.Location;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LocationMapper {
    /**
     * 插入一个地点
     * @param location
     */
    void insert(Location location);
}
