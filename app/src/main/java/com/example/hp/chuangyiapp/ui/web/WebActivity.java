package com.example.hp.chuangyiapp.ui.web;

import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.example.hp.chuangyiapp.R;
import com.example.hp.chuangyiapp.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WebActivity extends BaseActivity {

    private String url;
    private ChuangWebView webView;
    private LinearLayout errorLayout;

    private boolean isOnPause = false;

    public static void startWebActivity(Context context, String url){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        if(intent != null){
            url = intent.getStringExtra("url");
        }

        webView = findViewById(R.id.web_view);
        errorLayout = findViewById(R.id.error_layout);

        webView.setLayerType(View.LAYER_TYPE_HARDWARE,null);//开启硬件加速
        settingWebView();
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            //在开始加载网页时会回调
            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                errorLayout.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
                // 可以自己写一个加载Dialog
            }
            //加载错误的时候会回调
            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return;
                }
                errorLayout.setVisibility(View.VISIBLE);
                webView.setVisibility(View.INVISIBLE);
            }

            //加载错误的时候会回调
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                if (webResourceRequest.isForMainFrame()) {
                    errorLayout.setVisibility(View.VISIBLE);
                    webView.setVisibility(View.INVISIBLE);
                }
            }

            //加载完成的时候会回调
            @Override
            public void onPageFinished(WebView webView, String s) {
                //可以在这里取消加载Dialog
            }

        });
        webView.setWebChromeClient(new WebChromeClient());
    }

    private void settingWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
    }
    /**
     * 当Activity执行onPause()时让WebView执行pause
     */
    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (webView != null) {
                webView.getClass().getMethod("onPause").invoke(webView, (Object[]) null);
                isOnPause = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当Activity执行onResume()时让WebView执行resume
     */
    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (isOnPause) {
                if (webView != null) {
                    webView.getClass().getMethod("onResume").invoke(webView, (Object[]) null);
                }
                isOnPause = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 该处的处理尤为重要:
     * 应该在内置缩放控件消失以后,再执行mWebView.destroy()
     * 否则报错WindowLeaked
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.getSettings().setBuiltInZoomControls(true);
            webView.setVisibility(View.GONE);
            webView.destroy();
            webView = null;

        }
        isOnPause = false;
    }
}
