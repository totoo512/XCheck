package com.zyy.mapper;

import com.zyy.dto.UserLoginDTO;
import com.zyy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    User getByUsername(String username);
}
