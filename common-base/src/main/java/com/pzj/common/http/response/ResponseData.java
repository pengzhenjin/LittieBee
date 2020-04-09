package com.pzj.common.http.response;

/**
 * @ClassName: ResponseData
 * @Description: HTTP 的响应数据
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/20 13:55
 */
public class ResponseData<T> {
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 服务器是否响应成功
     *
     * @return
     */
    public boolean isSuccess() {
        return errorCode == 200 || errorCode == 0;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
