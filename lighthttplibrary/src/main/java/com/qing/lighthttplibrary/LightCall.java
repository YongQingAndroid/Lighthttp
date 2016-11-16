package com.qing.lighthttplibrary;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
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
public class LightCall<T> implements SuperCall {
    private Type type;
    private LightHttpRequest mRequest;
    public LightCall( Type type,LightHttpRequest mRequest){
        this.mRequest=mRequest;
        this.type=type;
    }
    public void call(final HCall<T> value) {
       new OkHttpClient().newCall(mRequest.getRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                value.onFaile(e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    T result=(T)mRequest.getCover().just(response,type,mRequest.getSoapElement());
                    value.onSuccess(result);
                } catch (Exception e) {
                    value.onFaile(e);
                }
            }
        });
    }
}
