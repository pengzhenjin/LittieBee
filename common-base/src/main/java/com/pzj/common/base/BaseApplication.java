package com.pzj.common.base;

import android.app.Application;
import android.content.Context;
import com.alibaba.android.arouter.launcher.ARouter;
import com.pzj.common.util.AppUtils;

/**
 * @ClassName: BaseApplication
 * @Description:
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/19 16:36
 */
public class BaseApplication extends Application {

    // 上下文
    private static Context context;

    // 是否是调试模式
    private static boolean isDebug;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initRouter(this);
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 是否是调试模式
     *
     * @return
     */
    public static boolean isDebug() {
        return AppUtils.isAppDebug(context);
    }

    private void initRouter(Application application) {
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
    }
}
