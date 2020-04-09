package com.pzj.common.http.interceptor;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @ClassName: HttpLoggerInterceptor
 * @Description: 日志拦截器
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/21 13:05
 */
public class HttpLoggerInterceptor {

    /**
     * 获取日志拦截器
     *
     * @return
     */
    public static HttpLoggingInterceptor getLoggerInterceptor() {
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(level);
        return interceptor;
    }
}
