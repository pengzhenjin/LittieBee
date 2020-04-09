package com.pzj.littiebee;

import com.pzj.common.base.BaseApplication;
import com.pzj.common.http.HttpManager;
import com.pzj.common.http.config.HttpConfig;
import com.pzj.common.util.LogUtils;

/**
 * @ClassName: AppApplication
 * @Description:
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/21 13:13
 */
public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
        initHttpManager();
    }

    /**
     * 初始化日志信息
     */
    private void initLog() {
        LogUtils.setTag("pzj");
        LogUtils.isOpenLog(isDebug());
    }

    /**
     * 初始化HttpManager
     */
    private void initHttpManager() {
        HttpConfig config = new HttpConfig.Builder()
                .baseUrl("https://www.wanandroid.com")
                .connectTimeout(10)
                .readTimeout(10)
                .writeTimeout(10)
                .openRetry(true)
                .openHttps(true)
                .openLogger(isDebug())
                .build();
        HttpManager.getInstance().init(config, getContext());
    }
}
