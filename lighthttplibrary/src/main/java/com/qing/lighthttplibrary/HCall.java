package com.qing.lighthttplibrary;

public interface HCall<T> {
        void onSuccess(T item);
        void onFaile(Exception e);
    }