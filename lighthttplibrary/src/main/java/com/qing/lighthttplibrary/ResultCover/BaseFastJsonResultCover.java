package com.qing.lighthttplibrary.ResultCover;

import com.alibaba.fastjson.JSON;
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
public class BaseFastJsonResultCover implements LightHttp.LightHttpCover {
    @Override
    public Object just(Response mObject, Type type,Object expandValue)throws Exception{
        String json=mObject.body().string();
        return JSON.parseObject(json,type);
    }
    public static BaseFastJsonResultCover creat(){
        return new BaseFastJsonResultCover();
    }
}
