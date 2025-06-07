package com.zyy.mapper;

import com.zyy.entity.Region;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegionMapper{
    /**
     * 插入区域
     * @param region
     */
    void insert(Region region);
}
