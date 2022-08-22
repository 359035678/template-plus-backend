package com.template.plus.common.framework.util;

import javax.servlet.http.HttpServletRequest;

/**
 * <code>
 * 浏览器工具类<br/>
 * 1.获取当前浏览器名称
 * 2.判断当前用户的浏览器
 * </code>
 *
 * @author geekidea
 * @since 2018-11-08
 */
public final class BrowserUtil {
    public static final String IE = "msie";
    public static final String FIREFOX = "firefox";
    public static final String CHROME = "chrome";

    private BrowserUtil() {
        throw new AssertionError();
    }

    /**
     * 获取当前浏览器名称
     *
     * @param request
     * @return 返回浏览器名称
     */
    public static String getCurrent(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (!"".equals(userAgent.trim())) {
            if (userAgent.contains(CHROME)) {
                return CHROME;
            } else if (userAgent.contains(FIREFOX)) {
                return FIREFOX;
            } else if (userAgent.contains(IE)) {
                return IE;
            }
        }
        return null;
    }

    /**
     * 是否是IE浏览器
     *
     * @param request
     * @return
     */
    public static boolean isIe(HttpServletRequest request) {
        return IE.equals(getCurrent(request));
    }

    /**
     * 是否是Firefox浏览器
     *
     * @param request
     * @return
     */
    public static boolean isFirefox(HttpServletRequest request) {
        return FIREFOX.equals(getCurrent(request));
    }

    /**
     * 是否是Chrome浏览器
     *
     * @param request
     * @return
     */
    public static boolean isChrome(HttpServletRequest request) {
        return CHROME.equals(getCurrent(request));
    }
}
