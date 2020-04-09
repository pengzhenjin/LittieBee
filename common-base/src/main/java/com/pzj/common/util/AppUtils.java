package com.pzj.common.util;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import java.io.File;

/**
 * @ClassName: AppUtils
 * @Description: app 工具类
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/19 17:00
 */
public class AppUtils {

    private AppUtils() {
        throw new UnsupportedOperationException("You can't instantiate me...");
    }

    /**
     * 获取ApplicationContext
     *
     * @param context
     * @return
     */
    private static Context getApplicationContext(Context context) {
        if (context instanceof Application) {
            return context;
        }
        return context.getApplicationContext();
    }

    /**
     * 安装APK
     *
     * @param context
     * @param filePath
     */
    public static void installAPK(Context context, String filePath) {
        File file = getFileByPath(filePath);
        installAPK(context, file);
    }

    /**
     * 安装APK
     *
     * @param context
     * @param file
     */
    public static void installAPK(Context context, File file) {
        if (!isFileExists(file)) {
            return;
        }
        context.startActivity(getInstallAPKIntent(context, file, true));
    }

    /**
     * 卸载APK
     *
     * @param context
     * @param packageName
     */
    public static void uninstallAPK(Context context, String packageName) {
        if (isSpace(packageName)) {
            return;
        }
        context.startActivity(getUninstallAPKIntent(packageName, true));
    }

    /**
     * 判断应用程序是否是调试模式
     *
     * @param context
     * @return
     */
    public static boolean isAppDebug(Context context) {
        return isAppDebug(context, context.getPackageName());
    }

    /**
     * 判断应用程序是否是调试模式
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppDebug(Context context, String packageName) {
        if (isSpace(packageName)) {
            return false;
        }
        try {
            Context c = getApplicationContext(context);
            PackageManager pm = c.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return ai != null && (ai.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取应用程序的包名
     *
     * @param context
     * @return
     */
    public static String getAppPackageName(Context context) {
        Context c = getApplicationContext(context);
        return c.getPackageName();
    }

    /**
     * 获取应用程序名
     *
     * @param context
     * @return
     */
    public static String getAppName(Context context) {
        return getAppName(context, context.getPackageName());
    }

    /**
     * 获取应用程序名
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getAppName(Context context, String packageName) {
        if (isSpace(packageName)) {
            return "";
        }
        try {
            Context c = getApplicationContext(context);
            PackageManager pm = c.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取应用程序的路径
     *
     * @param context
     * @return
     */
    public static String getAppPath(Context context) {
        return getAppPath(context, context.getPackageName());
    }

    /**
     * 获取应用程序的路径
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getAppPath(Context context, String packageName) {
        if (isSpace(packageName)) {
            return "";
        }
        try {
            Context c = getApplicationContext(context);
            PackageManager pm = c.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取应用程序的版本名称
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        return getAppVersionName(context, context.getPackageName());
    }

    /**
     * 获取应用程序的版本名称
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getAppVersionName(Context context, String packageName) {
        if (isSpace(packageName)) {
            return "";
        }
        try {
            Context c = getApplicationContext(context);
            PackageManager pm = c.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取应用程序的版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        return getAppVersionCode(context, context.getPackageName());
    }

    /**
     * 获取应用程序的版本号
     *
     * @param context
     * @param packageName
     * @return
     */
    public static int getAppVersionCode(Context context, String packageName) {
        if (isSpace(packageName)) {
            return -1;
        }
        try {
            Context c = getApplicationContext(context);
            PackageManager pm = c.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? -1 : pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取应用程序的Uid
     *
     * @param context
     * @return
     */
    public static int getAppUid(Context context) {
        return getAppUid(context, context.getPackageName());
    }

    /**
     * 获取应用程序的Uid
     *
     * @param context
     * @param packageName
     * @return
     */
    public static int getAppUid(Context context, String packageName) {
        try {
            Context c = getApplicationContext(context);
            ApplicationInfo ai = c.getPackageManager().getApplicationInfo(packageName, 0);
            if (ai != null) {
                return ai.uid;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取应用程序基本信息
     *
     * @param context
     * @return
     */
    public static AppInfo getAppInfo(Context context) {
        return getAppInfo(context, context.getPackageName());
    }

    /**
     * 获取应用程序基本信息
     *
     * @param context
     * @param packageName
     * @return
     */
    public static AppInfo getAppInfo(Context context, String packageName) {
        try {
            Context c = getApplicationContext(context);
            PackageManager pm = c.getPackageManager();
            if (pm == null) {
                return null;
            }
            return getBean(pm, pm.getPackageInfo(packageName, 0));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static AppInfo getBean(final PackageManager pm, final PackageInfo pi) {
        if (pi == null) {
            return null;
        }
        ApplicationInfo ai = pi.applicationInfo;
        String packageName = pi.packageName;
        String name = ai.loadLabel(pm).toString();
        Drawable icon = ai.loadIcon(pm);
        String packagePath = ai.sourceDir;
        String versionName = pi.versionName;
        int versionCode = pi.versionCode;
        boolean isSystem = (ApplicationInfo.FLAG_SYSTEM & ai.flags) != 0;
        return new AppInfo(packageName, name, icon, packagePath, versionName, versionCode, isSystem);
    }

    /**
     * 应用程序基本信息
     */
    public static class AppInfo {

        private String packageName;
        private String name;
        private Drawable icon;
        private String packagePath;
        private String versionName;
        private int versionCode;
        private boolean isSystem;

        public Drawable getIcon() {
            return icon;
        }

        public void setIcon(final Drawable icon) {
            this.icon = icon;
        }

        public boolean isSystem() {
            return isSystem;
        }

        public void setSystem(final boolean isSystem) {
            this.isSystem = isSystem;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(final String packageName) {
            this.packageName = packageName;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getPackagePath() {
            return packagePath;
        }

        public void setPackagePath(final String packagePath) {
            this.packagePath = packagePath;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(final int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(final String versionName) {
            this.versionName = versionName;
        }

        public AppInfo(String packageName, String name, Drawable icon, String packagePath, String versionName,
                int versionCode, boolean isSystem) {
            this.setName(name);
            this.setIcon(icon);
            this.setPackageName(packageName);
            this.setPackagePath(packagePath);
            this.setVersionName(versionName);
            this.setVersionCode(versionCode);
            this.setSystem(isSystem);
        }

        @Override
        public String toString() {
            return "{" +
                    "\n  pkg name: " + getPackageName() +
                    "\n  app icon: " + getIcon() +
                    "\n  app name: " + getName() +
                    "\n  app path: " + getPackagePath() +
                    "\n  app v name: " + getVersionName() +
                    "\n  app v code: " + getVersionCode() +
                    "\n  is system: " + isSystem() +
                    "}";
        }
    }

    private static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }

    private static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    private static boolean isSpace(String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static Intent getInstallAPKIntent(Context context, File file) {
        return getInstallAPKIntent(context, file, false);
    }

    private static Intent getInstallAPKIntent(Context context, File file, boolean isNewTask) {
        Context c = getApplicationContext(context);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data;
        String type = "application/vnd.android.package-archive";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            data = Uri.fromFile(file);
        } else {
            String authority = c.getPackageName() + ".utilcode.provider";
            data = FileProvider.getUriForFile(c, authority, file);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        c.grantUriPermission(c.getPackageName(), data, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(data, type);
        return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
    }

    private static Intent getUninstallAPKIntent(String packageName) {
        return getUninstallAPKIntent(packageName, false);
    }

    private static Intent getUninstallAPKIntent(String packageName, boolean isNewTask) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
    }
}
