package com.qing.lighthttplibrary;

import okhttp3.Request;

/**
 * Created by dell on 2016/10/25.
 */
public class LightHttpRequest {
    private Request request;
    private LightHttp.LightHttpCover cover;
    private String soapElement;
    public LightHttpRequest(Request request, LightHttp.LightHttpCover cover, String soapElement) {
        this.request = request;
        this.cover = cover;
        this.soapElement=soapElement;
    }

    public String getSoapElement() {
        return soapElement;
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
