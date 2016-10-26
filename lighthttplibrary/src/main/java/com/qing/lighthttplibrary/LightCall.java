package com.qing.lighthttplibrary;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
/**
 * Created by dell on 2016/10/25.
 */
public class LightCall<T> implements SuperCall{
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
                    T result=(T)mRequest.getCover().just(response,type);
                    value.onSuccess(result);
                } catch (Exception e) {
                    value.onFaile(e);
                }
            }
        });
    }
}
