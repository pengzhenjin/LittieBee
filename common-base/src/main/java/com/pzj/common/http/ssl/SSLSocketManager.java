package com.pzj.common.http.ssl;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @ClassName: SSLSocketManager
 * @Description:
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/21 10:39
 */
public class SSLSocketManager {

    /**
     * HTTPS单向认证
     *
     * @param protocolType 协议类型，如：TLS、SSL 等
     * @return
     */
    public static SSLSocketFactory getSSLSocketFactory(String protocolType) {
        try {
            SSLContext sslContext = SSLContext.getInstance(protocolType);
            sslContext.init(null, getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取TrustManager
     *
     * @return
     */
    private static TrustManager[] getTrustManagers() {
        X509TrustManager x509trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        TrustManager[] trustManagers = new TrustManager[] { x509trustManager };
        return trustManagers;
    }
}
