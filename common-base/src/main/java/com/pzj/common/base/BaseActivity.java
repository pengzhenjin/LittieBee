package com.pzj.common.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.pzj.common.util.StatusBarUtils;

/**
 * @ClassName: BaseActivity
 * @Description:
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/26 16:04
 */
public abstract class BaseActivity<D extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    protected D dataBinding;
    protected V viewModel;
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initParam();
        initViewDataBinding();
        setStatusBarColor();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // 黑色
        }
        initViewModel();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化参数
     */
    private void initParam() {

    }

    /**
     * 初始化ViewDataBinding
     */
    private void initViewDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutResId());
        dataBinding.setLifecycleOwner(this);
    }

    /**
     * 初始化ViewModel
     */
    private void initViewModel() {
        viewModel = viewModel == null ? getViewModel() : viewModel;
    }

    /**
     * 获取布局文件id
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * 获取ViewMode
     *
     * @return
     */
    protected abstract V getViewModel();

    /**
     * 设置状态栏颜色
     */
    public void setStatusBarColor() {
        StatusBarUtils.setColor(this, getResources().getColor(android.R.color.white), 0);
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }
}
