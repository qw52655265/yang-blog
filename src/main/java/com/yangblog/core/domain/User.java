package com.yangblog.core.domain;

import java.util.Date;

import lombok.Data;

/**
 * 用户表
 */
@Data
public class User {

    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码，MD5加密
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 出身日期
     */
    private Date birthday;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 性别，0-未知，1-男，2-女
     */
    private Byte gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 家庭住址
     */
    private String address;

    private String token;
}