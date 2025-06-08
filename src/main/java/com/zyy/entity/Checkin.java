package com.zyy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

/**
* 签到记录表
* @TableName checkin
*/
@Data
public class Checkin {

    /**
    * 签到记录ID
    */
    private Integer id;
    /**
    * 活动ID
    */
    private Integer activityId;
    /**
    * 实际签到位置地点
    */
    private Point geom;
    /**
    * 用户ID
    */
    private Integer userId;
    /**
    * 签到时间
    */
    private LocalDateTime checkinTime;

}
