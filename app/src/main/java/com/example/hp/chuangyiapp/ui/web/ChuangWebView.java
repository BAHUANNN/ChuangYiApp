package com.example.hp.chuangyiapp.ui.web;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ChuangWebView extends WebView {

    public ChuangWebView(Context context) {
        this(context,null);
    }

    public ChuangWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ChuangWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
