package com.template.plus.common.config.constant;

/**
 * <p>
 *  redis key 常量
 * </p>
 * @author geekidea
 * @date 2019-05-23
 **/
public interface CommonRedisKey {

    /**
     * 登录用户token信息key
     * login:token:tokenMd5
     */
    String LOGIN_TOKEN = "login:token:%s";

    /**
     * 登录用户信息key
     * login:user:username
     */
    String LOGIN_USER = "login:user:%s";

    /**
     * 登录用户盐值信息key
     * login:salt:username
     */
    String LOGIN_SALT= "login:salt:%s";

    /**
     * 登录用户username token
     * login:user:token:username:token
     */
    String LOGIN_USER_TOKEN = "login:user:token:%s:%s";

    /**
     * 登录用户下的所有token
     * login:user:token:username:*
     */
    String LOGIN_USER_ALL_TOKEN = "login:user:token:%s:*";

    /**
     * 验证码
     * verify.code:666666
     */
    String VERIFY_CODE = "verify.code:%s";
}
