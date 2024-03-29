package com.template.plus.common.framework.core.filter;

import com.template.plus.common.framework.util.IpUtil;
import com.template.plus.common.framework.core.bean.RequestDetail;
import com.template.plus.common.framework.core.util.RequestDetailThreadLocal;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 请求详情信息Filter
 *
 * @author geekidea
 * @date 2020/3/25
 **/
@Slf4j
public class RequestDetailFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("RequestDetailFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 设置请求详情信息
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 请求IP
        String ip = IpUtil.getRequestIp(httpServletRequest);
        // 请求路径
        String path = httpServletRequest.getRequestURI();
        RequestDetail requestDetail = new RequestDetail()
                .setIp(ip)
                .setPath(path);
        // 设置请求详情信息
        RequestDetailThreadLocal.setRequestDetail(requestDetail);
        chain.doFilter(request, response);
        // 释放
        RequestDetailThreadLocal.remove();
    }

    @Override
    public void destroy() {
        log.info("RequestDetailFilter destroy");
    }
}
