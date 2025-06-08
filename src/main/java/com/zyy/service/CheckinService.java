package com.zyy.service;

import com.zyy.dto.CheckinDTO;
import com.zyy.dto.CheckinListDTO;
import com.zyy.vo.CheckinVO;

import java.util.List;

public interface CheckinService {
    void checkin(CheckinDTO checkinDTO);

    List<CheckinVO> list(CheckinListDTO checkinListDTO);
}
