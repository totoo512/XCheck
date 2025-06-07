package com.zyy.entity;

import lombok.Data;

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
    * 实际签到位置地点ID
    */
    private Integer locationId;
    /**
    * 用户ID
    */
    private Integer userId;
    /**
    * 签到时间
    */
    private LocalDateTime checkinTime;
    /**
    * 创建时间
    */
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    private LocalDateTime updateTime;

}
