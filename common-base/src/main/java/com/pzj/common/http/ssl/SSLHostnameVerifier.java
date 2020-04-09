package com.pzj.common.http.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @ClassName: SSLHostnameVerifier
 * @Description:
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/21 10:44
 */
public class SSLHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
