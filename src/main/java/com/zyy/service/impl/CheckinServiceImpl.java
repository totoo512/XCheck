package com.zyy.service.impl;

import com.zyy.context.BaseContext;
import com.zyy.dto.CheckinDTO;
import com.zyy.dto.CheckinListDTO;
import com.zyy.dto.PointDTO;
import com.zyy.entity.Checkin;
import com.zyy.mapper.ActivityMapper;
import com.zyy.mapper.CheckinMapper;
import com.zyy.mapper.UserMapper;
import com.zyy.service.CheckinService;
import com.zyy.vo.CheckinVO;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckinServiceImpl implements CheckinService {

    @Autowired
    private CheckinMapper checkinMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 签到
     * @param checkinDTO
     */
    public void checkin(CheckinDTO checkinDTO) {
        Checkin checkin = new Checkin();
        BeanUtils.copyProperties(checkinDTO, checkin);

        // userId
        checkin.setUserId(BaseContext.getCurrentId());

        // checkinTime
        checkin.setCheckinTime(LocalDateTime.now());

        // geom
        GeometryFactory geometryFactory = new GeometryFactory();
        checkin.setGeom(geometryFactory.createPoint(new Coordinate(
                checkinDTO.getLocation().getLongitude(),
                checkinDTO.getLocation().getLatitude())));
        checkinMapper.insert(checkin);
    }

    /**
     * 查询签到列表
     * @param checkinListDTO
     * @return
     */
    public List<CheckinVO> list(CheckinListDTO checkinListDTO) {
        List<CheckinVO> checkinVOList = new ArrayList<>();
        List<Checkin> checkinList = checkinMapper.list(checkinListDTO);

        for (Checkin checkin : checkinList) {
            CheckinVO checkinVO = new CheckinVO();
            checkinVO.setId(checkin.getId());
            checkinVO.setActivityTitle(activityMapper.selectById(checkin.getActivityId()).getTitle());
            checkinVO.setLocation(new PointDTO(checkin.getGeom().getX(), checkin.getGeom().getY()));
            checkinVO.setUserName(userMapper.selectById(checkin.getUserId()).getName());
            checkinVO.setCheckinTime(checkin.getCheckinTime());

            checkinVOList.add(checkinVO);
        }
        return checkinVOList;
    }
}
