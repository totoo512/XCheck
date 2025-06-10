package com.zyy.service;

import com.zyy.dto.UserDTO;
import com.zyy.dto.UserLoginDTO;
import com.zyy.entity.User;
import com.zyy.vo.UserLoginVO;

import java.util.List;

public interface UserService {
    UserLoginVO login(UserLoginDTO userLoginDTO);

    UserLoginVO register(UserDTO userDTO);

    List<User> listAll();
}
