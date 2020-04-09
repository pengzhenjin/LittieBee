package com.pzj.main.ui.splash;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.android.arouter.launcher.ARouter;
import com.pzj.main.R;

/**
 * @ClassName: SplashActivity
 * @Description:
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/26 16:03
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gotoMainActivity();
    }

    private void gotoMainActivity() {
        ARouter.getInstance().build("/main/MainActivity").navigation();
        finish();
    }
}
