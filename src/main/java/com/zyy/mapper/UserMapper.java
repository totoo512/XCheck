package com.zyy.mapper;

import com.zyy.annotation.AutoFill;
import com.zyy.dto.UserLoginDTO;
import com.zyy.entity.User;
import com.zyy.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 新增用户
     * @param user
     */
    @AutoFill(OperationType.INSERT)
    void insert(User user);
}
