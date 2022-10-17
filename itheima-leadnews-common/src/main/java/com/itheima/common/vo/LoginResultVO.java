package com.itheima.common.vo;

import lombok.Data;

/**
 * 用户登录成功后统一的返回结果
 */
@Data
public class LoginResultVO {
    private String token;
    private Object user;
}
