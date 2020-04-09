package com.pzj.common.http.config;

/**
 * @ClassName: HttpConfig
 * @Description: HTTP 配置信息
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/19 14:44
 */
public class HttpConfig {
    private String baseUrl;
    private int connectTimeout = 10;
    private int readTimeout = 10;
    private int writeTimeout = 10;
    private boolean isOpenRetry;
    private boolean isOpenHttps;
    private boolean isOpenLogger;

    private HttpConfig(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.isOpenRetry = builder.isOpenRetry;
        this.isOpenHttps = builder.isOpenHttps;
        this.isOpenLogger = builder.isOpenLogger;
    }

    /**
     * 获取基础Url
     *
     * @return
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * 获取连接超时时间，默认10秒
     *
     * @return
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * 获取读取超时时间，默认10秒
     *
     * @return
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * 获取写入超时时间，默认10秒
     *
     * @return
     */
    public int getWriteTimeout() {
        return writeTimeout;
    }

    /**
     * 获取当网络连接失败时，是否开启重连，默认true
     *
     * @return
     */
    public boolean isOpenRetry() {
        return isOpenRetry;
    }

    /**
     * 获取是否开启HTTPS验证，默认false
     *
     * @return
     */
    public boolean isOpenHttps() {
        return isOpenHttps;
    }

    /**
     * 获取是否开启日志显示，默认true
     *
     * @return
     */
    public boolean isOpenLogger() {
        return isOpenLogger;
    }

    /**
     * Builder
     */
    public static final class Builder {
        private String baseUrl;
        private int connectTimeout = 10;
        private int readTimeout = 10;
        private int writeTimeout = 10;
        private boolean isOpenRetry = true;
        private boolean isOpenHttps = false;
        private boolean isOpenLogger = true;

        /**
         * 设置基础Url
         *
         * @param baseUrl
         * @return
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * 设置连接超时时间，默认10秒
         *
         * @param connectTimeout
         * @return
         */
        public Builder connectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        /**
         * 设置读取超时时间，默认10秒
         *
         * @param readTimeout
         * @return
         */
        public Builder readTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * 设置写入超时时间，默认10秒
         *
         * @param writeTimeout
         * @return
         */
        public Builder writeTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        /**
         * 设置当网络连接失败时，是否开启重连，默认true
         *
         * @param isOpenRetry
         * @return
         */
        public Builder openRetry(boolean isOpenRetry) {
            this.isOpenRetry = isOpenRetry;
            return this;
        }

        /**
         * 设置是否开启 HTTPS 验证，默认false
         *
         * @param isOpenHttps
         * @return
         */
        public Builder openHttps(boolean isOpenHttps) {
            this.isOpenHttps = isOpenHttps;
            return this;
        }

        /**
         * 设置是否开启日志显示，默认true
         *
         * @param isOpenLogger
         * @return
         */
        public Builder openLogger(boolean isOpenLogger) {
            this.isOpenLogger = isOpenLogger;
            return this;
        }

        /**
         * 构建HttpConfig
         *
         * @return
         */
        public HttpConfig build() {
            return new HttpConfig(this);
        }
    }
}
