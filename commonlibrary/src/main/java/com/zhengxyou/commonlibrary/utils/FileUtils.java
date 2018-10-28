package com.zhengxyou.commonlibrary.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;

import com.zhengxyou.commonlibrary.constant.Constants;

import java.io.File;
import java.io.IOException;

/**
 * 文件存储相关工具类
 */
public class FileUtils {

    /**
     * 从 URL 提取文件名
     * 创建一个以Url最后一个分割/后面部分文字为文件名的临时文件
     *
     * @param url 需要下载的文件url
     * @return 返回临时文件File
     */
    public static File getTempFile(Context context, String url) {
        File file = null;
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            if (fileName != null) {
                file = File.createTempFile(fileName, null, context.getCacheDir());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * @return 返回外部存储是否可读可写, 可写隐式包含可读
     */
    public static boolean isExternalStorageWriteable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * @return 返回外部存储是否可读
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    /**
     * 使用EnvironmentCompat工具类，判断File文件是否可读可写
     *
     * @param path File 文件
     * @return 返回某个文件是否可读可写
     */
    public static boolean isExternalStorageFileState(File path) {
        if (path == null) return false;
        String state = EnvironmentCompat.getStorageState(path);
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 在外部存储创建公共文件夹
     *
     * @param albumName 文件夹名
     * @param type      {0 and default:DIRECTORY_DOWNLOADS,
     *                  1:DIRECTORY_ALARMS,
     *                  2:DIRECTORY_DCIM,
     *                  3:DIRECTORY_DOCUMENTS,
     *                  4:DIRECTORY_MOVIES,
     *                  5:DIRECTORY_MUSIC,
     *                  6:DIRECTORY_NOTIFICATIONS,
     *                  7:DIRECTORY_PICTURES,
     *                  8:DIRECTORY_PODCASTS,
     *                  9:DIRECTORY_PODCASTS}
     * @return 文件夹file
     */
    public static File getAlbumStorageDir(String albumName, int type) {
        String fileType;
        switch (type) {
            case 1:
                fileType = Environment.DIRECTORY_ALARMS; //系统提醒铃声
                break;
            case 2:
                fileType = Environment.DIRECTORY_DCIM;//相机拍摄的图片和视频保存的位置
                break;
            case 3:
                fileType = Environment.DIRECTORY_DOCUMENTS;
                break;
            case 4:
                fileType = Environment.DIRECTORY_MOVIES;
                break;
            case 5:
                fileType = Environment.DIRECTORY_MUSIC;
                break;
            case 6:
                fileType = Environment.DIRECTORY_NOTIFICATIONS;
                break;
            case 7:
                fileType = Environment.DIRECTORY_PICTURES;//图片保存的位置
                break;
            case 8:
                fileType = Environment.DIRECTORY_PODCASTS;//系统广播存放
                break;
            case 9:
                fileType = Environment.DIRECTORY_RINGTONES;//铃声
                break;
            case 0:
            default:
                fileType = Environment.DIRECTORY_DOWNLOADS;
                break;
        }
        File file = new File(Environment.getExternalStoragePublicDirectory(fileType), albumName);
        if (!file.mkdirs()) {
            Log.e(Constants.TAG, "Directory not created");
        }
        return file;
    }

    /**
     * 在外部存储创建私有文件夹
     *
     * @param albumName 文件夹名
     * @param type      {0 and default:DIRECTORY_DOWNLOADS,
     *                  1:DIRECTORY_ALARMS,
     *                  2:DIRECTORY_DCIM,
     *                  3:DIRECTORY_DOCUMENTS,
     *                  4:DIRECTORY_MOVIES,
     *                  5:DIRECTORY_MUSIC,
     *                  6:DIRECTORY_NOTIFICATIONS,
     *                  7:DIRECTORY_PICTURES,
     *                  8:DIRECTORY_PODCASTS,
     *                  9:DIRECTORY_PODCASTS}
     * @return 文件夹file
     */
    public static File getAlbumStorageDir(Context context, String albumName, int type) {
        String fileType;
        switch (type) {
            case 1:
                fileType = Environment.DIRECTORY_ALARMS; //系统提醒铃声
                break;
            case 2:
                fileType = Environment.DIRECTORY_DCIM;//相机拍摄的图片和视频保存的位置
                break;
            case 3:
                fileType = Environment.DIRECTORY_DOCUMENTS;
                break;
            case 4:
                fileType = Environment.DIRECTORY_MOVIES;
                break;
            case 5:
                fileType = Environment.DIRECTORY_MUSIC;
                break;
            case 6:
                fileType = Environment.DIRECTORY_NOTIFICATIONS;
                break;
            case 7:
                fileType = Environment.DIRECTORY_PICTURES;//图片保存的位置
                break;
            case 8:
                fileType = Environment.DIRECTORY_PODCASTS;//系统广播存放
                break;
            case 9:
                fileType = Environment.DIRECTORY_RINGTONES;//铃声
                break;
            case 0:
            default:
                fileType = Environment.DIRECTORY_DOWNLOADS;
                break;
        }
        File file = new File(context.getExternalFilesDir(fileType), albumName);
        if (!file.mkdirs()) {
            Log.e(Constants.TAG, "Directory not created");
        }
        return file;
    }
}
