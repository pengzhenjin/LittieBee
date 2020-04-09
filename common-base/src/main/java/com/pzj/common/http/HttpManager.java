package com.pzj.common.http;

import android.content.Context;
import com.pzj.common.http.config.HttpConfig;
import com.pzj.common.http.interceptor.HttpLoggerInterceptor;
import com.pzj.common.http.ssl.SSLHostnameVerifier;
import com.pzj.common.http.ssl.SSLSocketManager;
import com.pzj.common.http.ssl.SSLTrustManager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName: HttpManager
 * @Description: HTTP 管理类
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/19 14:31
 */
public class HttpManager {
    private static volatile HttpManager mInstance;

    private HttpConfig mHttpConfig;
    private Context mContext;
    private Map<HttpConfig, Retrofit> mRetrofitMap;

    private HttpManager() {
        mRetrofitMap = new ConcurrentHashMap<>();
    }

    public static HttpManager getInstance() {
        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {
                    mInstance = new HttpManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化
     *
     * 该方法应该在 Application 类中 onCreate() 方法中调用
     *
     * @param httpConfig
     * @param context
     */
    public void init(HttpConfig httpConfig, Context context) {
        this.mHttpConfig = httpConfig;
        this.mContext = context;
    }

    /**
     * 获取Retrofit
     *
     * @return
     */
    public Retrofit getRetrofit() {
        return getRetrofit(mHttpConfig);
    }

    /**
     * 获取Retrofit
     *
     * @param httpConfig
     * @return
     */
    public Retrofit getRetrofit(HttpConfig httpConfig) {
        if (httpConfig == null) {
            throw new NullPointerException("HttpConfig cannot be empty");
        }

        Retrofit retrofit = mRetrofitMap.get(httpConfig);
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(httpConfig.getConnectTimeout(), TimeUnit.SECONDS);
            builder.readTimeout(httpConfig.getReadTimeout(), TimeUnit.SECONDS);
            builder.writeTimeout(httpConfig.getWriteTimeout(), TimeUnit.SECONDS);
            builder.retryOnConnectionFailure(httpConfig.isOpenRetry());
            if (httpConfig.isOpenHttps()) {
                builder.sslSocketFactory(SSLSocketManager.getSSLSocketFactory("TLS"), new SSLTrustManager());
                builder.hostnameVerifier(new SSLHostnameVerifier());
            }
            if (httpConfig.isOpenLogger()) {
                builder.addInterceptor(HttpLoggerInterceptor.getLoggerInterceptor());
            }

            retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(httpConfig.getBaseUrl())
                    .build();

            mRetrofitMap.put(httpConfig, retrofit);
        }

        return retrofit;
    }

    /**
     * 获取ApiService
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T getApiService(Class<T> service) {
        Retrofit retrofit = getRetrofit();
        return retrofit.create(service);
    }

    /**
     * 获取ApiService
     *
     * @param httpConfig
     * @param service
     * @param <T>
     * @return
     */
    public <T> T getApiService(HttpConfig httpConfig, Class<T> service) {
        Retrofit retrofit = getRetrofit(httpConfig);
        return retrofit.create(service);
    }
}
