package com.zyy.mapper;

import com.zyy.entity.Location;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LocationMapper {
    /**
     * 插入一个地点
     * @param location
     */
    void insert(Location location);

    /**
     * 根据id查询地点
     * @param activityId
     * @return
     */
    Location selectByActivityId(Integer activityId);

    /**
     * 根据id删除地点
     * @param activityId
     */
    @Delete("delete from location where activity_id = #{activityId}")
    void deleteByActivityId(Integer activityId);
}
