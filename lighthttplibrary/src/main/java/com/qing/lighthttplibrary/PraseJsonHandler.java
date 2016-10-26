package com.qing.lighthttplibrary;
import android.util.Log;

import com.qing.lighthttplibrary.annotation.MethodManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * This network framework is based on the production of okhttp
 * Network framework free open source, and the final right to interpret the author.
 * The author will go regularly to update the business code, but no obligation to notify the user.
 * My open source community account is fengling136
 * Welcome attention
 * Thank you for your use
 * the power by ZYQ
 */
public class PraseJsonHandler implements InvocationHandler {
        private LightHttp.Builder builder;
        public PraseJsonHandler(LightHttp.Builder builder) {
            super();
           this.builder=builder;
        }
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try{
                MethodManager methodManager=new MethodManager(builder.getMsrc(),method,args);
                return new LightHttpClite(methodManager.getHttpRequest(),builder.getCover(),methodManager.getreturnType(),builder.getAdapterCover()).build();
            }catch (Exception e){
                Log.e("qing",e.getMessage());
            }
            return new Exception("is null");
        }
    }