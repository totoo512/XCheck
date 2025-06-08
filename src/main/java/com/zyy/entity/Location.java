package com.zyy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    private Integer id;
    private String name;
    private Point geom;
    private Integer activityId;

}
