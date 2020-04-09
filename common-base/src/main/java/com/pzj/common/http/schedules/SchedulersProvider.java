package com.pzj.common.http.schedules;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName: SchedulersProvider
 * @Description: 线程调度器
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/20 14:14
 */
public class SchedulersProvider {

    public static Scheduler computation() {
        return Schedulers.computation();
    }

    public static Scheduler io() {
        return Schedulers.io();
    }

    public static Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(io()).observeOn(ui());
            }
        };
    }
}
