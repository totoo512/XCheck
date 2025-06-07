package com.zyy.service;

import com.zyy.dto.UserDTO;
import com.zyy.dto.UserLoginDTO;
import com.zyy.vo.UserLoginVO;

public interface UserService {
    UserLoginVO login(UserLoginDTO userLoginDTO);

    void save(UserDTO userDTO);
}
