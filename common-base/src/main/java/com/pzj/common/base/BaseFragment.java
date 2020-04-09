package com.pzj.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

/**
 * @ClassName: BaseFragment
 * @Description:
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/26 17:58
 */
public abstract class BaseFragment<D extends ViewDataBinding, V extends BaseViewModel> extends Fragment {

    protected D dataBinding;
    protected V viewModel;
    private Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        dataBinding = initViewDataBinding(inflater, getLayoutResId(), container);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化参数
     */
    private void initParam() {

    }

    /**
     * 初始化ViewDataBinding
     *
     * @param inflater
     * @param layoutId
     * @param container
     * @return
     */
    private D initViewDataBinding(LayoutInflater inflater, int layoutId, ViewGroup container) {
        dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        dataBinding.setLifecycleOwner(this);
        return dataBinding;
    }

    /**
     * 初始化ViewModel
     *
     * @return
     */
    private V initViewModel() {
        viewModel = viewModel == null ? getViewModel() : viewModel;
        return viewModel;
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
     * 初始化数据
     */
    private void initData() {

    }
}
