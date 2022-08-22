package com.template.plus.common.framework.common.exception;


import com.template.plus.common.framework.common.api.ApiCode;

/**
 * 验证码校验异常
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class VerificationCodeException extends SpringBootPlusException {
	private static final long serialVersionUID = -2640690119865434398L;

	public VerificationCodeException(String message) {
        super(message);
    }

    public VerificationCodeException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public VerificationCodeException(ApiCode apiCode) {
        super(apiCode);
    }
}
