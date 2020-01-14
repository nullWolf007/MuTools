package com.mumu.kernel.file;

import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;

/**
 * to be a better man.
 *
 * @author nullWolf
 * @date 2020/1/14
 */
public class MuFileTool {


    /**
     * 得到SD卡根目录.
     */
    public static File getSDCardRootPath() {
        File path = null;
        if (sdCardIsAvailable()) {
            path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
        } else {
            path = Environment.getDataDirectory();
        }
        return path;
    }

    /**
     * SD卡是否可用.
     */
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            return sd.canWrite();
        } else {
            return false;
        }
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getFileByPath(@NonNull String filePath) {
        return new File(filePath);
    }

    /**
     * 判断该路径文件是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean fileExists(@NonNull String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


    public static boolean createOrExistsDir(@NonNull String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file 文件
     * @return {@code true}: 存在或创建成功<br>{@code false}: 不存在或创建失败
     */
    public static boolean createOrExistsDir(File file) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }
}
