package com.pzj.common.http.observable;

import com.pzj.common.http.exception.ServerException;
import com.pzj.common.http.exception.ExceptionHandler;
import com.pzj.common.http.response.ResponseData;
import io.reactivex.observers.DisposableObserver;

/**
 * @ClassName: HttpObserver
 * @Description:
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/20 15:11
 */
public abstract class HttpObserver<T> extends DisposableObserver<ResponseData<T>> {

    public HttpObserver() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(ResponseData<T> responseData) {
        if (responseData.isSuccess()) {
            T data = responseData.getData();
            onSuccess(data);
        } else {
            int code = responseData.getErrorCode();
            String message = responseData.getErrorMsg();
            ServerException exception = new ServerException(code, message);
            onError(exception);
        }
    }

    /**
     * 回调正常数据
     *
     * @param data
     */
    protected abstract void onSuccess(T data);

    /**
     * 异常处理，包括两方面数据：
     * (1) 服务端没有没有返回数据（如：网络异常，连接超时等）
     * (2) 服务端返回了数据，但返回的data为空（如：密码错误，App登陆超时，token失效等）
     */
    @Override
    public void onError(Throwable e) {
        ExceptionHandler.handleException(e);
    }

    @Override
    public void onComplete() {

    }
}
