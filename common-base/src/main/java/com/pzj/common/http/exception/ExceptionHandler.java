package com.pzj.common.http.exception;

import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.JsonParseException;
import com.pzj.common.base.BaseApplication;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import org.json.JSONException;

/**
 * @ClassName: ExceptionHandler
 * @Description: 异常处理类
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/20 16:04
 */
public class ExceptionHandler {
    private static final String TAG = "ExceptionHandler";

    private static final String BAD_NETWORK = "服务器异常，请稍后再试";
    private static final String CONNECT_ERROR = "网络连接失败，请检查网络";
    private static final String CONNECT_TIMEOUT = "连接超时，请稍后再试";
    private static final String PARSE_ERROR = "解析服务器响应数据失败";
    private static final String UNKNOWN_ERROR = "未知错误";

    private static final String SERVER_RESPONSE_ERROR = "服务器返回数据失败";

    public static void handleException(Throwable e) {
        if (e instanceof retrofit2.HttpException) {
            showExceptionMessage(BAD_NETWORK);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            showExceptionMessage(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            showExceptionMessage(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            showExceptionMessage(PARSE_ERROR);
        } else if (e instanceof ServerException) {
            // 服务端返回的错误信息（如：40001=token失效，40002=密码错误）
            ServerException exception = (ServerException) e;
            handleServerException(exception.getCode(), exception.getMessage());
        } else {
            showExceptionMessage(UNKNOWN_ERROR);
        }
    }

    /**
     * 根据业务逻辑处理服务器返回的异常信息
     *
     * @param code
     * @param message
     */
    private static void handleServerException(int code, String message) {
        Log.d(TAG, "code: " + code + "，message：" + message);
        if (message == null) {
            Toast.makeText(BaseApplication.getContext(), SERVER_RESPONSE_ERROR, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BaseApplication.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示异常信息
     *
     * @param message
     */
    private static void showExceptionMessage(String message) {
        Log.d(TAG, "HttpException: " + message);
        Toast.makeText(BaseApplication.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
