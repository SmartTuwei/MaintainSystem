package com.example.xidian.utils;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;


public class JavaScriptinterface extends Object{

    Context context;
    public JavaScriptinterface(Context c,WebView mWebView) {
        context= c;
    }
    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void hello(String msg) {
           Toast.makeText(context, "hello",Toast.LENGTH_LONG).show();
           System.out.println("JS调用了Android的hello方法");
    }

}