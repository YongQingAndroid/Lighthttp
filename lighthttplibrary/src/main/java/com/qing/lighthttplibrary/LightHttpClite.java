package com.qing.lighthttplibrary;

import com.qing.lighthttplibrary.annotation.MethodManager;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import rx.Observable;

/**
 * This network framework is based on the production of okhttp
 * Network framework free open source, and the final right to interpret the author.
 * The author will go regularly to update the business code, but no obligation to notify the user.
 * My open source community account is fengling136
 * Welcome attention
 * Thank you for your use
 * the power by ZYQ
 */
public class LightHttpClite {
    private okhttp3.Request request;
    private LightHttp.LightHttpCover cover;
    private ParameterizedType type;
    private String soapElement;
    private LightHttp.LightAdapterCover adapterCover;
    public LightHttpClite(MethodManager methodmanager , LightHttp.LightHttpCover cover, LightHttp.LightAdapterCover adapterCover) {
        this.cover = cover;
        this.type = methodmanager.getreturnType();
        this.request = methodmanager.getHttpRequest();
        this.adapterCover=adapterCover;
        this.soapElement=methodmanager.getSoapxml();
    }

    public Object build() {
        Type resultType = type.getActualTypeArguments()[0];
        Type rawtype = type.getRawType();
        LightHttpRequest mLightHttpRequest=new LightHttpRequest(request,cover,soapElement);
        if (rawtype.equals(Observable.class)) {
            return Observable.create((Observable.OnSubscribe)adapterCover.adapter(resultType,mLightHttpRequest));
        }else if(rawtype.equals(LightCall.class)){
            return new LightCall(resultType,mLightHttpRequest);
        }
        return new Exception("no type");
    }

}
