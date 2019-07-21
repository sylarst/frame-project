package com.age.mac.baselibrary.utils;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.math.BigDecimal;

public class Dirs {

    /**
     * 应用在sdcard上的存储根目录
     * @return
     */
    public static String getSDCardCacheDir() {
        return Environment.getExternalStorageDirectory().getPath() + "/baselib";
    }

    /**
     * 创建目录
     * @param dir   :要创建的目录路径
     * @return      :目录
     */
    public static File createDirs(String dir) {
        if (dir == null || dir.length() == 0) return null;
        File file = new File(dir);
        if (!file.exists()) {
            return file.mkdirs() ? file : null;
        } else {
            return file;
        }
    }

    /**
     * 应用在sdcard上的 cache 目录
     * @return
     */
    public static String getCacheDir() {
        return getSDCardCacheDir() + "/cache";
    }
    public static String getH5CacheDir(){
        return getCacheDir() + "/h5";
    }
    public static String getNetWorkCacheDir(){
        return getCacheDir() + "/network";
    }

    public static String getImageDir(){
        return getSDCardCacheDir() + "/image";
    }
    /**
     * 应用在sdcard上的 tmp 目录
     * @return
     */
    public static String getTmpDir() {
        return getSDCardCacheDir() + "/tmp";
    }
    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath filePath
     * @param deleteThisPath deleteThisPath
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
