package com.pzj.common.util;

import java.io.File;
import java.util.Locale;

/**
 * @ClassName: FileUtils
 * @Description: 文件工具类
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/26 21:06
 */
public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("You can't instantiate me...");
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath
     * @return
     */
    public static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean isFileExists(final String filePath) {
        return isFileExists(getFileByPath(filePath));
    }

    /**
     * 判断文件是否存在
     *
     * @param file
     * @return
     */
    public static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }

    /**
     * 文件重命名
     *
     * @param filePath
     * @param newName
     * @return
     */
    public static boolean rename(final String filePath, final String newName) {
        return rename(getFileByPath(filePath), newName);
    }

    /**
     * 文件重命名
     *
     * @param file
     * @param newName
     * @return
     */
    public static boolean rename(final File file, final String newName) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return false;
        }
        if (isSpace(newName)) {
            return false;
        }
        if (newName.equals(file.getName())) {
            return true;
        }
        File newFile = new File(file.getParent() + File.separator + newName);
        return !newFile.exists() && file.renameTo(newFile);
    }

    /**
     * 判断是否是一个目录
     *
     * @param dirPath
     * @return
     */
    public static boolean isDir(final String dirPath) {
        return isDir(getFileByPath(dirPath));
    }

    /**
     * 判断是否是一个目录
     *
     * @param file
     * @return
     */
    public static boolean isDir(final File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    /**
     * 判断是否是一个文件
     *
     * @param filePath
     * @return
     */
    public static boolean isFile(final String filePath) {
        return isFile(getFileByPath(filePath));
    }

    /**
     * 判断是否是一个文件
     *
     * @param file
     * @return
     */
    public static boolean isFile(final File file) {
        return file != null && file.exists() && file.isFile();
    }

    /**
     * 删除目录
     *
     * @param dirPath
     * @return
     */
    public static boolean deleteDir(final String dirPath) {
        return deleteDir(getFileByPath(dirPath));
    }

    /**
     * 删除目录及文件
     *
     * @param dir
     * @return
     */
    public static boolean deleteDir(final File dir) {
        if (dir == null) {
            return false;
        }
        if (!dir.exists()) {
            return true;
        }
        if (!dir.isDirectory()) {
            return false;
        }
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) {
                        return false;
                    }
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) {
                        return false;
                    }
                }
            }
        }
        return dir.delete();
    }

    /**
     * 删除文件
     *
     * @param srcFilePath
     * @return
     */
    public static boolean deleteFile(final String srcFilePath) {
        return deleteFile(getFileByPath(srcFilePath));
    }

    /**
     * 删除文件
     *
     * @param file
     * @return
     */
    public static boolean deleteFile(final File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }

    /**
     * 获取目录的大小
     *
     * @param dirPath
     * @return
     */
    public static String getDirSize(final String dirPath) {
        return getDirSize(getFileByPath(dirPath));
    }

    /**
     * 获取目录的大小
     *
     * @param dir
     * @return
     */
    public static String getDirSize(final File dir) {
        long len = getDirLength(dir);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }

    /**
     * 获取文件大小
     *
     * @param filePath
     * @return
     */
    public static String getFileSize(final String filePath) {
        long len = getFileLength(filePath);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }

    /**
     * 获取文件大小
     *
     * @param file
     * @return
     */
    public static String getFileSize(final File file) {
        long len = getFileLength(file);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }

    /**
     * 获取目录的长度
     *
     * @param dirPath
     * @return
     */
    public static long getDirLength(final String dirPath) {
        return getDirLength(getFileByPath(dirPath));
    }

    /**
     * 获取目录的长度
     *
     * @param dir
     * @return
     */
    public static long getDirLength(final File dir) {
        if (!isDir(dir)) {
            return -1;
        }
        long len = 0;
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    len += getDirLength(file);
                } else {
                    len += file.length();
                }
            }
        }
        return len;
    }

    /**
     * 获取文件的长度
     *
     * @param filePath
     * @return
     */
    public static long getFileLength(final String filePath) {
        if (isSpace(filePath)) {
            return -1;
        }
        File file = new File(filePath);
        return file.length();
    }

    /**
     * 获取文件的长度
     *
     * @param file
     * @return
     */
    public static long getFileLength(final File file) {
        if (!isFile(file)) {
            return -1;
        }
        return file.length();
    }

    /**
     * 获取目录名
     *
     * @param file
     * @return
     */
    public static String getDirName(final File file) {
        if (file == null) {
            return "";
        }
        return getDirName(file.getAbsolutePath());
    }

    /**
     * 获取目录名
     *
     * @param filePath
     * @return
     */
    public static String getDirName(final String filePath) {
        if (isSpace(filePath)) {
            return "";
        }
        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? "" : filePath.substring(0, lastSep + 1);
    }

    /**
     * 获取文件名
     *
     * @param file
     * @return
     */
    public static String getFileName(final File file) {
        if (file == null) {
            return "";
        }
        return getFileName(file.getAbsolutePath());
    }

    /**
     * 获取文件名
     *
     * @param filePath
     * @return
     */
    public static String getFileName(final String filePath) {
        if (isSpace(filePath)) {
            return "";
        }
        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? filePath : filePath.substring(lastSep + 1);
    }

    /**
     * 获取文件的扩展名
     *
     * @param file
     * @return
     */
    public static String getFileExtension(final File file) {
        if (file == null) {
            return "";
        }
        return getFileExtension(file.getPath());
    }

    /**
     * 获取文件的扩展名
     *
     * @param filePath
     * @return
     */
    public static String getFileExtension(final String filePath) {
        if (isSpace(filePath)) {
            return "";
        }
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi) {
            return "";
        }
        return filePath.substring(lastPoi + 1);
    }

    private static String byte2FitMemorySize(final long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        } else if (byteNum < 1024) {
            return String.format(Locale.getDefault(), "%.3fB", (double) byteNum);
        } else if (byteNum < 1048576) {
            return String.format(Locale.getDefault(), "%.3fKB", (double) byteNum / 1024);
        } else if (byteNum < 1073741824) {
            return String.format(Locale.getDefault(), "%.3fMB", (double) byteNum / 1048576);
        } else {
            return String.format(Locale.getDefault(), "%.3fGB", (double) byteNum / 1073741824);
        }
    }

    private static boolean isSpace(final String s) {
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
}
