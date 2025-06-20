package com.zyy.entity;

import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

/**
* 活动表
* @TableName activity
*/
@Data
public class Activity {

    /**
    * 活动ID
    */
    private Integer id;
    /**
    * 创建者ID
    */
    private Integer createUser;
    /**
     * 半径
     */
    private Integer radius;
    /**
    * 活动标题
    */
    private String title;
    /**
    * 开始时间
    */
    private LocalDateTime startTime;
    /**
    * 结束时间
    */
    private LocalDateTime endTime;
    /**
    * 简介
    */
    private String description;
    /**
    * 创建时间
    */
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    private LocalDateTime updateTime;

}
