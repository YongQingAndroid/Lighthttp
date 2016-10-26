package com.qing.lighthttplibrary.adapterCover;
import com.qing.lighthttplibrary.LightHttp;
import com.qing.lighthttplibrary.LightHttpRequest;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by dell on 2016/10/25.
 */
public class BaseRxAdapterCover implements LightHttp.LightAdapterCover<Observable.OnSubscribe> {
    @Override
    public Observable.OnSubscribe adapter(final Type type, final LightHttpRequest mRequest) {
        return new Observable.OnSubscribe<Object>() {
            @Override
            public void call(final Subscriber<? super Object> subscriber) {
                new OkHttpClient().newCall(mRequest.getRequest()).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        subscriber.onError(e);
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try{
                            subscriber.onNext(mRequest.getCover().just(response,type));
                        }catch (Throwable e){
                            subscriber.onError(e);
                        }
                    }
                });
            }
        };
    }
    public static BaseRxAdapterCover creat(){
        return new BaseRxAdapterCover();
    }
}
