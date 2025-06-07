package com.zyy.controller;

import com.zyy.dto.ActivityDTO;
import com.zyy.result.Result;
import com.zyy.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
@Slf4j
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 新增活动
     * @param activityDTO
     * @return
     */
    @PostMapping()
    public Result save(@RequestBody ActivityDTO activityDTO) {
        log.info("新增活动: {}", activityDTO);
        activityService.save(activityDTO);
        return Result.success();
    }
}
