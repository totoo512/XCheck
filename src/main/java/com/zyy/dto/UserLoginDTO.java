package com.zyy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    /**
     * 用户名（通常用学号）
     */
    private String username;
    /**
     * 密码
     */
    private String password;

}
