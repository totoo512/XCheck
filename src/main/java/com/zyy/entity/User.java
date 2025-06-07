package com.zyy.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
* 用户表
* @TableName user
*/
@Data
public class User {

    /**
    * 用户ID
    */
    private Integer id;
    /**
    * 用户名（通常用学号）
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
    * 用户昵称
    */
    private String name;
    /**
    * 创建时间
    */
    private LocalDateTime createTime;
    /**
    * 修改时间
    */
    private LocalDateTime updateTime;

}
