package com.zyy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {

    /**
     * 活动id
     */
    private Integer id;
    /**
     * 位置
     */
    private PointDTO location;
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
     * 活动介绍
     */
    private String description;

}
