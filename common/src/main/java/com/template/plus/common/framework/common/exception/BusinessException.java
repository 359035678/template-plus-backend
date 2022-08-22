package com.template.plus.common.framework.common.exception;


import com.template.plus.common.framework.common.api.ApiCode;

/**
 * 业务异常
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class BusinessException extends SpringBootPlusException {
	private static final long serialVersionUID = -2303357122330162359L;

	public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessException(ApiCode apiCode) {
        super(apiCode);
    }

}
