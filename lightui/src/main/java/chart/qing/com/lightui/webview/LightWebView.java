package chart.qing.com.lightui.webview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
/**
 * Modified release based on native webview,
 * Support web location and function interoperability, support tell, file tags
 *Support JS popups support log output, support the right decision
 * My open source community account is fengling136
 * Welcome attention
 * Thanks for your use
 * the power by ZYQ
 */
public class LightWebView extends WebView{
    private Activity context;
    public LightWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSeting();
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
        this.context=context;
    }
    public void setWebViewPageFinished(WebViewPageFinished callback){
        this.setWebViewClient(new MyWebClient(context,callback));
        this.setWebChromeClient(new MyWebChromeClient(context));
    }
    public interface WebViewPageFinished{
        void onFinished(WebView view, String url);
    }
}
