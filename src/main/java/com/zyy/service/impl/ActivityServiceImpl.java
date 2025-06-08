package com.zyy.service.impl;

import com.zyy.context.BaseContext;
import com.zyy.dto.ActivityDTO;
import com.zyy.dto.PointDTO;
import com.zyy.entity.Activity;
import com.zyy.entity.Location;
import com.zyy.entity.Region;
import com.zyy.entity.User;
import com.zyy.mapper.ActivityMapper;
import com.zyy.mapper.LocationMapper;
import com.zyy.mapper.RegionMapper;
import com.zyy.mapper.UserMapper;
import com.zyy.service.ActivityService;
import com.zyy.vo.ActivityVO;
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
    @Autowired
    private UserMapper userMapper;

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
        location.setGeom(point);
        locationMapper.insert(location);
        activity.setLocationId(location.getId());

        // 插入 region 废弃
        /*Region region = new Region();
        PointDTO[] pointDTOS = activityDTO.getRegion();

        Coordinate[] coords = new Coordinate[pointDTOS.length];
        for (int i = 0; i < pointDTOS.length; i++) {
            coords[i] = new Coordinate(pointDTOS[i].getLongitude(), pointDTOS[i].getLatitude());
        }
        Polygon polygon = geometryFactory.createPolygon(coords);
        region.setBoundary(polygon.toString());
        regionMapper.insert(region);
        activity.setRegionId(region.getId());*/

        activityMapper.insert(activity);
    }

    /**
     * 根据id查询活动
     * @param id
     * @return
     */
    public ActivityVO getById(Integer id) {
        ActivityVO activityVO = new ActivityVO();
        Activity activity = activityMapper.selectById(id);
        BeanUtils.copyProperties(activity, activityVO);

        // 设置地点坐标
//        Region region = regionMapper.selectById(activity.getRegionId());
        Location location = locationMapper.selectById(activity.getLocationId());
        activityVO.setLocation(new PointDTO(
                location.getGeom().getX(),
                location.getGeom().getY()));

        // 设置创建者名称
        User user = userMapper.selectById(activity.getCreateUser());
        activityVO.setCreateName(user.getName());

        return activityVO;
    }
}
