package com.zyy.mapper;

import com.zyy.dto.CheckinListDTO;
import com.zyy.entity.Checkin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CheckinMapper {
    /**
     * 插入签到信息
     * @param checkin
     */
    void insert(Checkin checkin);

    /**
     * 查询签到信息
     * @param checkinListDTO
     * @return
     */
    List<Checkin> list(CheckinListDTO checkinListDTO);

    /**
     * 删除和活动ID关联的签到信息
     * @param activityId
     */
    @Delete("delete from checkin where activity_id = #{activityId}")
    void deleteByActivityId(Integer activityId);

    /**
     * 判断签到信息是否在活动范围内
     * @param checkin
     */
    boolean isWithin(Checkin checkin);

    /**
     * 判断用户是否已经签到过
     * @param activityId
     * @param userId
     * @return
     */
    @Select("select count(0) from checkin where activity_id = #{activityId} and user_id = #{userId}")
    Integer selectByActivityIdAndUserId(Integer activityId, Integer userId);
}
