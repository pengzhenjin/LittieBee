package com.pzj.common.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import java.util.List;

/**
 * @ClassName: NetworkUtils
 * @Description: 网络工具类
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/19 16:46
 */
public class NetworkUtils {

    private NetworkUtils() {
        throw new UnsupportedOperationException("You can't instantiate me...");
    }

    public enum NetworkType {
        NETWORK_ETHERNET, // 以太网
        NETWORK_WIFI, // WIFI
        NETWORK_4G, // 4G
        NETWORK_3G, // 3G
        NETWORK_2G, // 2G
        NETWORK_UNKNOWN, // UNKNOWN
        NETWORK_NO // 无网络
    }

    /**
     * 检测网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager cm = getConnectivityManager(context);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable();
        }

        return false;
    }

    /**
     * 检测是否是wifi连接
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = getConnectivityManager(context);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    /**
     * 检测是否是4G连接
     *
     * @param context
     * @return
     */
    public static boolean is4gConnected(Context context) {
        ConnectivityManager cm = getConnectivityManager(context);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable()
                    && networkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
        }
        return false;
    }

    /**
     * 检测GPS是否打开
     *
     * @param context
     * @return
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> accessibleProviders = lm.getProviders(true);
        for (String name : accessibleProviders) {
            if ("gps".equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取网络类型
     *
     * @param context
     * @return
     */
    public static NetworkType getNetworkType(Context context) {
        if (isEthernet(context)) {
            return NetworkType.NETWORK_ETHERNET;
        }
        ConnectivityManager cm = getConnectivityManager(context);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return NetworkType.NETWORK_WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {
                    case TelephonyManager.NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        return NetworkType.NETWORK_2G;

                    case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        return NetworkType.NETWORK_3G;

                    case TelephonyManager.NETWORK_TYPE_IWLAN:
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        return NetworkType.NETWORK_4G;

                    default:
                        String subtypeName = info.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            return NetworkType.NETWORK_3G;
                        } else {
                            return NetworkType.NETWORK_UNKNOWN;
                        }
                }
            } else {
                return NetworkType.NETWORK_UNKNOWN;
            }
        }
        return NetworkType.NETWORK_NO;
    }

    private static boolean isEthernet(Context context) {
        ConnectivityManager cm = getConnectivityManager(context);
        if (cm == null) {
            return false;
        }
        NetworkInfo info = cm.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        if (info == null) {
            return false;
        }
        NetworkInfo.State state = info.getState();
        if (null == state) {
            return false;
        }
        return state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING;
    }

    private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
