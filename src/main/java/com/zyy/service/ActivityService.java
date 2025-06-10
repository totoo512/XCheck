package com.zyy.service;

import com.zyy.dto.ActivityDTO;
import com.zyy.dto.ActivityListDTO;
import com.zyy.dto.PointDTO;
import com.zyy.vo.ActivityListByLocationVO;
import com.zyy.vo.ActivityVO;

import java.util.List;

public interface ActivityService {
    void save(ActivityDTO activityDTO);

    ActivityVO getById(Integer id);

    List<ActivityVO> listQuery(ActivityListDTO activityListDTO);

    void deleteById(Integer id);

    void update(ActivityDTO activityDTO);

    List<ActivityListByLocationVO> listByLocation(PointDTO pointDTO);

    List<ActivityVO> listMyActivities();
}
