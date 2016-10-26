package com.qing.lighthttplibrary.ResultCover;
import android.util.Log;

import com.qing.lighthttplibrary.LightHttp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * This network framework is based on the production of okhttp
 * Network framework free open source, and the final right to interpret the author.
 * The author will go regularly to update the business code, but no obligation to notify the user.
 * My open source community account is fengling136
 * Welcome attention
 * Thanks for your use
 * the power by ZYQ
 */
public class BaseStreamResultCover implements LightHttp.LightHttpCover {
    protected File file;
    protected static String defult_file = "/sdcard/lighthttp/";
    @Override
    public Object just(Response mObject, Type type)throws Exception{
        InputStream inputStream = mObject.body().byteStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file == null ? new File(defult_file + "defult") : new File(defult_file + file));
        byte[] buffer = new byte[1024*3];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
        }
        fileOutputStream.flush();
        Log.d(LightHttp.TAG, "文件下载成功...");
        return null;
    }
    public static BaseStreamResultCover creat(File ...file) {
        if(file!=null&&file.length==1){
            return new BaseStreamResultCover(file[0]);
        }
        return new BaseStreamResultCover();
    }
    public BaseStreamResultCover(File file){
        this.file=file;
    }
    public BaseStreamResultCover(){
    }
}
