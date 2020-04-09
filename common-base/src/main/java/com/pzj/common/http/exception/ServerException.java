package com.pzj.common.http.exception;

/**
 * @ClassName: ServerException
 * @Description: 服务器返回的异常信息
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/20 15:59
 */
public class ServerException extends RuntimeException {

    private int code;
    private String message;

    public ServerException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ServerException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}