package com.zyy.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginVO {

    /**
     * 用户ID
     */
    private Integer id;
    /**
     * 用户名（通常用学号）
     */
    private String username;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * jwt令牌
     */
    private String token;

}
