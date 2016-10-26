package com.qing.lighthttplibrary.ResultCover;
import com.google.gson.Gson;
import com.qing.lighthttplibrary.LightHttp;

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
public class BaseGsonResultCover implements LightHttp.LightHttpCover {
    @Override
    public Object just(Response mObject, Type type)throws Exception{
        return new Gson().fromJson(mObject.body().string(),type);
    }
    public static BaseGsonResultCover creat(){
        return new BaseGsonResultCover();
    }
}
