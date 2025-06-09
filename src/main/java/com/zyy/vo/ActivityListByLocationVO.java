package com.zyy.vo;

import com.zyy.dto.PointDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityListByLocationVO {

    /**
     * 活动ID
     */
    private Integer id;
    /**
     * 创建者名称
     */
    private String createName;
    /**
     * 距离
     */
    private Double distance;
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

}
