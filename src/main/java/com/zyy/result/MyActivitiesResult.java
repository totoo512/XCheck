package com.zyy.result;

import com.zyy.vo.ActivityVO;
import lombok.Data;

import java.util.List;

@Data
public class MyActivitiesResult {

    private String name;
    private List<ActivityVO> activities;

}
