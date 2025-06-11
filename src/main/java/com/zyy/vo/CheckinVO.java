package com.zyy.vo;

import com.zyy.dto.PointDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckinVO {

    private Integer id;
    private String activityTitle;
    private PointDTO location;
    private String name;
    private String username;
    private LocalDateTime checkinTime;

}
