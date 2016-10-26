package com.qing.lighthttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.qing.lighthttplibrary.LightHttp;
import com.qing.lighthttplibrary.ResultCover.BaseFastJsonResultCover;
import com.qing.lighthttplibrary.adapterCover.BaseRxAdapterCover;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test).setOnClickListener(this::lightHttpTest);
    }
    private void lightHttpTest(View v){
        LightHttp lighthttp=  new LightHttp.Builder()
                .src("http://192.168.1.97/")
                .addResultCover(BaseFastJsonResultCover.creat())
                .addAdapterCover(BaseRxAdapterCover.creat())
                .build();
        lighthttp.creat(Server.class).getdata()
                .flatMap(Observable::from)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{Toast.makeText(this,item.getName(),Toast.LENGTH_SHORT).show();}, error -> Log.e("zyq", error.getMessage()));
    }
}
