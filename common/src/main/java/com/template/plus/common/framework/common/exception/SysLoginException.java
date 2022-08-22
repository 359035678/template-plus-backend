package com.template.plus.common.framework.common.exception;


import com.template.plus.common.framework.common.api.ApiCode;

/**
 * 系统登录异常
 *
 * @author geekidea
 * @date 2019-08-04
 */
public class SysLoginException extends SpringBootPlusException {
	private static final long serialVersionUID = -3157438982569715170L;

	public SysLoginException(String message) {
        super(message);
    }

    public SysLoginException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public SysLoginException(ApiCode apiCode) {
        super(apiCode);
    }
}
