package com.zyy.service.impl;

import com.zyy.constant.MessageConstant;
import com.zyy.context.BaseContext;
import com.zyy.dto.ActivityDTO;
import com.zyy.dto.ActivityListDTO;
import com.zyy.dto.PointDTO;
import com.zyy.entity.Activity;
import com.zyy.entity.Location;
import com.zyy.entity.User;
import com.zyy.exception.ActivityNotFoundException;
import com.zyy.mapper.ActivityMapper;
import com.zyy.mapper.CheckinMapper;
import com.zyy.mapper.LocationMapper;
import com.zyy.mapper.UserMapper;
import com.zyy.service.ActivityService;
import com.zyy.vo.ActivityListByLocationVO;
import com.zyy.vo.ActivityVO;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private LocationMapper locationMapper;
//    @Autowired
//    private RegionMapper regionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CheckinMapper checkinMapper;

    /**
     * 新增活动
     * @param activityDTO
     */
    @Transactional
    public void save(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);

        // 设置创建者ID
        activity.setCreateUser(BaseContext.getCurrentId());

        // 插入 activity
        activityMapper.insert(activity);

        // location
        // 插入 location
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(
                activityDTO.getLocation().getLongitude(),
                activityDTO.getLocation().getLatitude()));
        Location location = new Location();
        location.setGeom(point);
        location.setActivityId(activity.getId());
        locationMapper.insert(location);
    }

    /**
     * 根据id查询活动
     * @param id
     * @return
     */
    public ActivityVO getById(Integer id) {
        ActivityVO activityVO = new ActivityVO();
        Activity activity = activityMapper.selectById(id);
        if (activity == null) {
            throw new ActivityNotFoundException(MessageConstant.ACTIVITY_NOT_FOUND);
        }
        BeanUtils.copyProperties(activity, activityVO);

        // 设置地点坐标
        Location location = locationMapper.selectByActivityId(activity.getId());
        activityVO.setLocation(new PointDTO(
                location.getGeom().getX(),
                location.getGeom().getY()));

        // 设置创建者名称
        User user = userMapper.selectById(activity.getCreateUser());
        activityVO.setCreateName(user.getName());

        return activityVO;
    }

    /**
     * 活动列表按条件查询
     * @param activityListDTO
     * @return
     */
    public List<ActivityVO> listQuery(ActivityListDTO activityListDTO) {
        List<ActivityVO> activityVOList = new ArrayList<>();
        List<Activity> activityList = activityMapper.listQuery(activityListDTO);

        for (Activity activity : activityList) {
            ActivityVO activityVO = new ActivityVO();
            BeanUtils.copyProperties(activity, activityVO);

            // 设置创建者名称
            User user = userMapper.selectById(activity.getCreateUser());
            activityVO.setCreateName(user.getName());

            // 设置地点坐标
            Location location = locationMapper.selectByActivityId(activity.getId());
            activityVO.setLocation(new PointDTO(
                    location.getGeom().getX(),
                    location.getGeom().getY()));
            activityVOList.add(activityVO);
        }

        return activityVOList;
    }

    /**
     * 删除活动
     * @param id
     */
    @Transactional
    public void deleteById(Integer id) {
        activityMapper.deleteById(id);
        locationMapper.deleteByActivityId(id);
        checkinMapper.deleteByActivityId(id);
    }

    /**
     * 修改活动
     * @param activityDTO
     */
    @Transactional
    public void update(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);
        // 不修改create_user
        activityMapper.update(activity);

        // 修改location
        GeometryFactory geometryFactory = new GeometryFactory();
        Location  location = Location.builder()
                .activityId(activityDTO.getId())
                .geom(geometryFactory.createPoint(new Coordinate(
                        activityDTO.getLocation().getLongitude(),
                        activityDTO.getLocation().getLatitude())))
                .build();
        locationMapper.update(location);
    }

    /**
     * 根据位置查询活动列表
     *
     * @param pointDTO
     * @return
     */
    public List<ActivityListByLocationVO> listByLocation(PointDTO pointDTO) {
        return activityMapper.listByLocation(pointDTO);
    }

    /**
     * 查询当前登录用户创建的活动
     * @return
     */
    public List<ActivityVO> listMyActivities() {
        List<ActivityVO> activityVOList = new ArrayList<>();
        List<Activity> activityList = activityMapper.listByUserId(BaseContext.getCurrentId());

        for (Activity activity : activityList) {
            ActivityVO activityVO = new ActivityVO();
            BeanUtils.copyProperties(activity, activityVO);

            // 设置创建者名称
            User user = userMapper.selectById(activity.getCreateUser());
            activityVO.setCreateName(user.getName());

            // 设置地点坐标
            Location location = locationMapper.selectByActivityId(activity.getId());
            activityVO.setLocation(new PointDTO(
                    location.getGeom().getX(),
                    location.getGeom().getY()));
            activityVOList.add(activityVO);
        }

        return activityVOList;
    }
}
