package com.zyy.service.impl;

import com.zyy.constant.MessageConstant;
import com.zyy.context.BaseContext;
import com.zyy.dto.CheckinDTO;
import com.zyy.dto.CheckinListDTO;
import com.zyy.dto.PointDTO;
import com.zyy.entity.Checkin;
import com.zyy.entity.User;
import com.zyy.exception.CheckinFailedException;
import com.zyy.mapper.ActivityMapper;
import com.zyy.mapper.CheckinMapper;
import com.zyy.mapper.UserMapper;
import com.zyy.service.CheckinService;
import com.zyy.vo.CheckinVO;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
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
        if (checkinDTO.getLocation() == null
                || checkinDTO.getLocation().getLatitude() == null
                || checkinDTO.getLocation().getLongitude() == null) {
            throw new CheckinFailedException(MessageConstant.POSITIONING_FAILED);
        }

        if (checkinMapper.selectByActivityIdAndUserId(checkinDTO.getActivityId(), BaseContext.getCurrentId()) != 0) {
            throw new CheckinFailedException(MessageConstant.ALREADY_CHECKIN);
        }

        Checkin checkin = new Checkin();
        BeanUtils.copyProperties(checkinDTO, checkin);

        // geom
        GeometryFactory geometryFactory = new GeometryFactory();
        checkin.setGeom(geometryFactory.createPoint(new Coordinate(
                checkinDTO.getLocation().getLongitude(),
                checkinDTO.getLocation().getLatitude())));
        if (!checkinMapper.isWithin(checkin)) {
            throw new CheckinFailedException(MessageConstant.CHECKIN_OUT_OF_RANGE);
        }


        // userId
        checkin.setUserId(BaseContext.getCurrentId());

        // checkinTime
        checkin.setCheckinTime(LocalDateTime.now());

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
            User user = userMapper.selectById(checkin.getUserId());

            CheckinVO checkinVO = CheckinVO.builder()
                    .id(checkin.getId())
                    .activityTitle(activityMapper.selectById(checkin.getActivityId()).getTitle())
                    .location(new PointDTO(checkin.getGeom().getX(), checkin.getGeom().getY()))
                    .name(user.getName())
                    .username(user.getUsername())
                    .checkinTime(checkin.getCheckinTime())
                    .build();

            checkinVOList.add(checkinVO);
        }
        return checkinVOList;
    }
}
