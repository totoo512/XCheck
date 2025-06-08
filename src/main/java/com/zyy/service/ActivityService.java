package com.zyy.service;

import com.zyy.dto.ActivityDTO;
import com.zyy.vo.ActivityVO;

public interface ActivityService {
    void save(ActivityDTO activityDTO);

    ActivityVO getById(Integer id);
}
