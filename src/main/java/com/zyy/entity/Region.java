package com.zyy.entity;

import lombok.Data;
import org.locationtech.jts.geom.Polygon;

@Data
public class Region {

    private Integer id;
    private String name;
    // TODO: Polygon类型转换器
    private Polygon boundary;

}
