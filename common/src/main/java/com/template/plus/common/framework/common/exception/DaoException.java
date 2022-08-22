package com.template.plus.common.framework.common.exception;


import com.template.plus.common.framework.common.api.ApiCode;

/**
 * DAO异常
 *
 * @author geekidea
 * @date 2018-11-08
 */
public class DaoException extends SpringBootPlusException {
	private static final long serialVersionUID = -6912618737345878854L;

	public DaoException(String message) {
        super(message);
    }

    public DaoException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public DaoException(ApiCode apiCode) {
        super(apiCode);
    }
}
