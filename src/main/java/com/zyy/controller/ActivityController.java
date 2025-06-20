package com.zyy.controller;

import com.zyy.context.BaseContext;
import com.zyy.dto.ActivityDTO;
import com.zyy.dto.ActivityListDTO;
import com.zyy.dto.PointDTO;
import com.zyy.result.MyActivitiesResult;
import com.zyy.result.Result;
import com.zyy.service.ActivityService;
import com.zyy.vo.ActivityListByLocationVO;
import com.zyy.vo.ActivityVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 活动列表按条件查询
     * @param activityListDTO
     * @return
     */
    @GetMapping("/list")
    public Result<List<ActivityVO>> list(ActivityListDTO activityListDTO) {
        log.info("活动列表按条件查询: {}", activityListDTO);
        List<ActivityVO> activityVOList = activityService.listQuery(activityListDTO);
        return Result.success(activityVOList);
    }

    /**
     * 删除活动
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除活动: {}", id);
        activityService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改活动
     * @param activityDTO
     * @return
     */
    @PutMapping()
    public Result update(@RequestBody ActivityDTO activityDTO) {
        log.info("修改活动: {}", activityDTO);
        activityService.update(activityDTO);
        return Result.success();
    }

    /**
     * 根据位置查询活动
     * @param pointDTO
     * @return
     */
    @GetMapping("/listByLocation")
    public Result<List<ActivityListByLocationVO>> listByLocation(PointDTO pointDTO) {
        log.info("根据位置查询活动: {}", pointDTO);
        List<ActivityListByLocationVO> activityListByLocationVOList = activityService.listByLocation(pointDTO);
        return Result.success(activityListByLocationVOList);
    }

    /**
     * 查询当前登录用户创建的活动
     * @return
     */
    @GetMapping("/listMyActivities")
    public Result<MyActivitiesResult> listMyActivities() {
        log.info("查询当前登录用户创建的活动: {}", BaseContext.getCurrentId());
        MyActivitiesResult myActivitiesResult = activityService.listMyActivities();
        return Result.success(myActivitiesResult);
    }
}
