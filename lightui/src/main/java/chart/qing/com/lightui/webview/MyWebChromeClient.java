package chart.qing.com.lightui.webview;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by dell on 2016/9/9.
 */
public class MyWebChromeClient extends WebChromeClient {
    private Activity context;
    private ValueCallback<Uri> mUploadMessage;

    MyWebChromeClient(Activity context) {
        this.context = context;
    }

    // Android > 4.1.1 调用这个方法
    public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                String acceptType, String capture) {
        mUploadMessage = uploadMsg;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        context.startActivityForResult(
                Intent.createChooser(intent, "完成操作需要使用"),
                1000);
    }

    public void clearUploadMessage() {
        mUploadMessage = null;
    }

    public ValueCallback<Uri> getmUploadMessage() {
        return mUploadMessage;
    }

    // 3.0 + 调用这个方法
    public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                String acceptType) {
        mUploadMessage = uploadMsg;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        context.startActivityForResult(
                Intent.createChooser(intent, "完成操作需要使用"),
                1000);
    }

    // Android < 3.0 调用这个方法
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        context.startActivityForResult(
                Intent.createChooser(intent, "完成操作需要使用"),
                1000);

    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        mBuilder.setTitle("alert(提示)");
        mBuilder.setMessage(message);
        mBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                result.confirm();
            }
        });
        mBuilder.setCancelable(false);
        mBuilder.create().show();
        return true;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle("confirm");
        b.setMessage(message);
        b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                result.confirm();
            }
        });
        b.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                result.cancel();
            }
        });
        b.create().show();
        return true;
    }



    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.e("console", "[" + consoleMessage.messageLevel() + "] " + consoleMessage.message() + "(" + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber() + ")");
        return super.onConsoleMessage(consoleMessage);
    }
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, false);
        super.onGeolocationPermissionsShowPrompt(origin, callback);
    }
//    @Override
//    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota,
//                                        long estimatedSize, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
//        quotaUpdater.updateQuota(estimatedSize * 2);
//    }
}
