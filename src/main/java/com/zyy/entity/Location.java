package com.zyy.entity;

import lombok.Data;
import org.locationtech.jts.geom.Point;

@Data
public class Location {

    private Integer id;
    private String name;
    private Point geom;

}
