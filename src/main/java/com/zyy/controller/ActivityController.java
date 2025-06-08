package com.zyy.controller;

import com.zyy.dto.ActivityDTO;
import com.zyy.result.Result;
import com.zyy.service.ActivityService;
import com.zyy.vo.ActivityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据id查询活动
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<ActivityVO> getById(@PathVariable Integer id) {
        log.info("根据id查询活动: {}", id);
        ActivityVO activityVO = activityService.getById(id);
        return Result.success(activityVO);
    }
}
