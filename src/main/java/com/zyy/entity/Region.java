package com.zyy.entity;

import lombok.Data;
import org.locationtech.jts.geom.Polygon;

@Data
public class Region {

    private Integer id;
    private String name;
    private String boundary;

}
