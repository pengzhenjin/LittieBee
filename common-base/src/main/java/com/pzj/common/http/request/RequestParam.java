package com.pzj.common.http.request;

import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ClassName: RequestParam
 * @Description: 请求参数类
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/19 22:04
 */
public class RequestParam {

    private Map<String, Object> mParams;

    public RequestParam() {
        mParams = new LinkedHashMap<>();
    }

    /**
     * 添加参数
     *
     * @param key
     * @param value
     */
    public void addParameter(String key, Object value) {
        if (mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(key, value);
    }

    public void addParameter(String key, int value) {
        addParameter(key, String.valueOf(value));
    }

    public void addParameter(String key, long value) {
        addParameter(key, String.valueOf(value));
    }

    public void addParameter(String key, float value) {
        addParameter(key, String.valueOf(value));
    }

    public void addParameter(String key, double value) {
        addParameter(key, String.valueOf(value));
    }

    /**
     * 获取请求参数
     *
     * @return Map
     */
    public Map<String, Object> getParameter() {
        if (mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        return mParams;
    }

    /**
     * 将请求参数转换为json格式
     *
     * @return
     */
    public String toJson() {
        if (mParams.isEmpty()) {
            return null;
        }
        JSONObject json = new JSONObject();
        for (Map.Entry<String, Object> entry : mParams.entrySet()) {
            try {
                json.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return json.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        for (Map.Entry<String, Object> entry : mParams.entrySet()) {
            stringBuffer.append(entry.getKey());
            stringBuffer.append(":");
            stringBuffer.append(entry.getValue());
            stringBuffer.append("\t");
        }
        return stringBuffer.toString();
    }
}
