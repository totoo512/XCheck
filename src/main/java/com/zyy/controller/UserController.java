package com.zyy.controller;

import com.zyy.dto.UserDTO;
import com.zyy.dto.UserLoginDTO;
import com.zyy.entity.User;
import com.zyy.result.Result;
import com.zyy.service.UserService;
import com.zyy.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO);
        UserLoginVO userLoginVO = userService.login(userLoginDTO);
        return Result.success(userLoginVO);
    }

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    public Result<UserLoginVO> register(@RequestBody UserDTO userDTO) {
        log.info("新增用户：{}", userDTO);
        UserLoginVO userLoginVO = userService.register(userDTO);
        return Result.success(userLoginVO);
    }

    /**
     * 查询全部用户信息
     * @return
     */
    @GetMapping("/listAll")
    public Result<List<User>> listAll() {
        log.info("查询全部用户信息");
        List<User> userList = userService.listAll();
        return Result.success(userList);
    }
}
