package com.yangblog.core.domain.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserView {

    private Integer id;

    /**
     * 账号
     */
    private String account;

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
