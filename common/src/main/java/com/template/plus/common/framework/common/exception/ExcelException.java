package com.template.plus.common.framework.common.exception;

import com.template.plus.common.framework.common.api.ApiCode;

public class ExcelException extends SpringBootPlusException {
    private static final long serialVersionUID = -6912618737345878854L;

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public ExcelException(ApiCode apiCode) {
        super(apiCode);
    }
}
