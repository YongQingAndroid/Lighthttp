package com.qing.lighthttplibrary;
import okhttp3.Request;
/**
 * Created by dell on 2016/10/25.
 */
public class LightHttpRequest {
   private Request request;
   private LightHttp.LightHttpCover cover;
    public LightHttpRequest(Request request, LightHttp.LightHttpCover cover){
        this.request=request;
        this.cover=cover;
    }
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public LightHttp.LightHttpCover getCover() {
        return cover;
    }

    public void setCover(LightHttp.LightHttpCover cover) {
        this.cover = cover;
    }
}
