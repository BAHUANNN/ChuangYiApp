package com.example.hp.chuangyiapp.block.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;

import com.example.hp.chuangyiapp.R;

public class WebActivity extends AppCompatActivity {

    private String url;
    private ChuangWebView webView;

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
        webView.setLayerType(View.LAYER_TYPE_HARDWARE,null);//开启硬件加速
        settingWebView();
        webView.loadUrl(url);
        webView.setWebViewClient(new ChuangWebView.ChuangWebViewClient());
        webView.setWebChromeClient(new ChuangWebView.ChuangWebChromeClient());
    }

    private void settingWebView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
    }
}
