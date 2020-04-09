package com.pzj.main.ui.main;

import androidx.lifecycle.ViewModelProviders;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.pzj.common.base.BaseActivity;
import com.pzj.main.R;
import com.pzj.main.databinding.ActivityMainBinding;

/**
 * @ClassName: MainActivity
 * @Description:
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/26 16:08
 */
@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel getViewModel() {
        return ViewModelProviders.of(this).get(MainViewModel.class);
    }
}
