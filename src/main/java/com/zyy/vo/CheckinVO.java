package com.zyy.vo;

import com.zyy.dto.PointDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CheckinVO {

    private Integer id;
    private String activityTitle;
    private PointDTO location;
    private String userName;
    private LocalDateTime checkinTime;

}
