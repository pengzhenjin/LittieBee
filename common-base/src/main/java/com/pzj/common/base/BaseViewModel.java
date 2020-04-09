package com.pzj.common.base;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @ClassName: BaseViewModel
 * @Description:
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/26 17:06
 */
public class BaseViewModel extends AndroidViewModel {

    private CompositeDisposable compositeDisposable;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    protected void addDisposable(Disposable disposable) {
        if (this.compositeDisposable == null) {
            this.compositeDisposable = new CompositeDisposable();
        }
        this.compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (this.compositeDisposable != null && !compositeDisposable.isDisposed()) {
            this.compositeDisposable.clear();
        }
    }
}
