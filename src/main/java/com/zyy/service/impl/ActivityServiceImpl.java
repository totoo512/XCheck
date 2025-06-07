package com.zyy.service.impl;

import com.zyy.context.BaseContext;
import com.zyy.dto.ActivityDTO;
import com.zyy.dto.PointDTO;
import com.zyy.entity.Activity;
import com.zyy.entity.Location;
import com.zyy.entity.Region;
import com.zyy.mapper.ActivityMapper;
import com.zyy.mapper.LocationMapper;
import com.zyy.mapper.RegionMapper;
import com.zyy.service.ActivityService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private RegionMapper regionMapper;

    /**
     * 新增活动
     * @param activityDTO
     */
    @Transactional
    public void save(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);

        activity.setCreateUser(BaseContext.getCurrentId());

        // locationId regionId
        // 插入 location
        GeometryFactory geometryFactory = new GeometryFactory();
        Location location = new Location();
        // 若需要可以给地点命名
        Point point = geometryFactory.createPoint(new Coordinate(
                activityDTO.getLocation().getLongitude(),
                activityDTO.getLocation().getLatitude()));
        location.setGeom(point.toString());
        locationMapper.insert(location);

        // 插入 region
        Region region = new Region();
        PointDTO[] pointDTOS = activityDTO.getRegion();

        Coordinate[] coords = new Coordinate[pointDTOS.length];
        for (int i = 0; i < pointDTOS.length; i++) {
            coords[i] = new Coordinate(pointDTOS[i].getLongitude(), pointDTOS[i].getLatitude());
        }
        Polygon polygon = geometryFactory.createPolygon(coords);
        region.setBoundary(polygon.toString());
        regionMapper.insert(region);

        activity.setLocationId(location.getId());
        activity.setRegionId(region.getId());

        activityMapper.insert(activity);
    }
}
