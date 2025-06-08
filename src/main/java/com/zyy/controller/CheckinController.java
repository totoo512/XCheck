package com.zyy.controller;

import com.zyy.dto.CheckinDTO;
import com.zyy.dto.CheckinListDTO;
import com.zyy.entity.Checkin;
import com.zyy.result.Result;
import com.zyy.service.CheckinService;
import com.zyy.vo.CheckinVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkin")
@Slf4j
public class CheckinController {

    @Autowired
    private CheckinService checkinService;

    /**
     * 签到
     * @param checkinDTO
     * @return
     */
    @PostMapping()
    public Result checkin(@RequestBody CheckinDTO checkinDTO) {
        log.info("签到: {}", checkinDTO);
        checkinService.checkin(checkinDTO);
        return Result.success();
    }

    /**
     * 查询签到记录
     * @param checkinListDTO
     * @return
     */
    @GetMapping("/list")
    public Result<List<CheckinVO>> list(CheckinListDTO checkinListDTO) {
        log.info("查询签到记录: {}", checkinListDTO);
        List<CheckinVO> checkinList = checkinService.list(checkinListDTO);
        return Result.success(checkinList);
    }
}
