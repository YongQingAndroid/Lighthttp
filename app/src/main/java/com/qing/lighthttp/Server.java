package com.qing.lighthttp;
import com.qing.lighthttplibrary.LightCall;
import com.qing.lighthttplibrary.annotation.LightHttpGet;
import java.util.List;
import rx.Observable;

/**
 * Created by dell on 2016/10/26.
 */
public interface Server {
    @LightHttpGet("login.php")
    Observable<List<People>> getdata();
    @LightHttpGet("login.php")
    LightCall<List<People>> getDefultData();
}
