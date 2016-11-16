package chart.qing.com.lightui.webview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
/**
 * Created by dell on 2016/11/16.
 */
public class LightWebView extends WebView{
    public LightWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private void initSeting(){
        this.getSettings().setJavaScriptEnabled(true);
        this.getSettings().setDomStorageEnabled(true);
        this.getSettings().setAppCacheMaxSize(1024*1024*8);
        String appCachePath = getContext().getApplicationContext().getCacheDir().getAbsolutePath();
        this.getSettings().setAppCachePath(appCachePath);
        this.getSettings().setAllowFileAccess(true);
        this.getSettings().setAppCacheEnabled(true);
        String dir = getContext().getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        this.getSettings().setGeolocationDatabasePath(dir);
    }
    public void inItWebView(Activity context){
       this.setWebViewClient(new MyWebClient(context));
       this.setWebChromeClient(new MyWebChromeClient(context));
    }
}
