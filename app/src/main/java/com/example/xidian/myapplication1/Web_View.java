package com.example.xidian.myapplication1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xidian.utils.JavaScriptinterface;
import com.example.xidian.utils.MyApi;
import com.example.xidian.utils.Mylog;

import java.util.ArrayList;
import java.util.List;

public class Web_View extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private DrawerLayout drawerLayout;
    private TextView textView;
    private static final String TAG = "Web_View";
    public WebView webcontentbox;
    private TextView callbackindex;
    private TextView MyBtn;
    private TextView peopleData;
    private TextView Userbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        callbackindex = findViewById(R.id.Callbackindex);
        MyBtn = findViewById(R.id.MyBtn);
        peopleData = findViewById(R.id.peopleData);
        Userbtn = findViewById(R.id.Userbtn);
        webcontentbox = (WebView)findViewById(R.id.webcontentbox);
        //允许调用js代码
        webcontentbox.getSettings().setJavaScriptEnabled(true);
        //允许chromet调试 api大于19才可以调用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webcontentbox.setWebContentsDebuggingEnabled(true);
        }
        webcontentbox.getSettings().setBuiltInZoomControls(true);
        webcontentbox.getSettings().setAllowFileAccess(true);
        webcontentbox.setWebChromeClient(new WebChromeClient());
        Intent _intent = getIntent();
        String pageNum = _intent.getStringExtra("pageNum");
        webcontentbox.addJavascriptInterface(new JavaScriptinterface(this,webcontentbox), "test");
        GetenterPage(pageNum);
        callbackindex.setOnClickListener(this);
        Userbtn.setOnClickListener(this);
        peopleData.setOnClickListener(this);
        MyBtn.setOnClickListener(this);
        initView();

    }
    @SuppressLint("SetJavaScriptEnabled")
    private void GetenterPage(String pageNum) {
        Mylog.i(TAG,TAG+pageNum);
        switch(Integer.valueOf(pageNum )){
            case 0 :
                webcontentbox.loadUrl(MyApi.WEBVIEW_URL+"/wheel_diameter.html");
                break;
            case 1 :

                webcontentbox.loadUrl(MyApi.WEBVIEW_URL+"/wheel_setadd.html");
                break;
            case 2 :
                webcontentbox.loadUrl(MyApi.WEBVIEW_URL+"/external_door.html");
                break;
            case 3 :
                webcontentbox.loadUrl(MyApi.WEBVIEW_URL+"/the_wheeladd.html");
                break;
        }
    }
    /*
     *  底部点击事件;
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Callbackindex:
                Goindex();
                break;
            case R.id.MyBtn:
                GetenterPage(String.valueOf(1));
                break;
            case R.id.peopleData:
                GetenterPage(String.valueOf(2));
                break;
            case R.id.Userbtn:
                GetenterPage(String.valueOf(3));
                break;
        }
    }
     // 去首页
    private void Goindex() {
        Intent intent = new Intent(Web_View.this,HomeMainActivity.class);
        startActivity(intent);
    }

    // 侧滑菜单
    private void initView()
    {
        listView=(ListView) findViewById(R.id.v4_listview);
        drawerLayout=(DrawerLayout) findViewById(R.id.v4_drawerlayout);
        textView=(TextView) findViewById(R.id.v4_text);
        initDate();
    }

    private void initDate(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MyApi.PAGE_LIST);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Getpage(position);
                        break;
                    case 1:
                        Getpage(position);
                        break;
                    case 2:
                        Getpage(position);
                        break;
                    case 3:
                        Getpage(position);
                        break;
                }

                showDrawerLayout();
            }
        });
        //drawerLayout.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
    }
    /**
     * 跳转页面的方法
     *
     */
    private void Getpage(int posotion_num) {
        String pageIndex = posotion_num + "";
        this.GetenterPage(pageIndex);
    }
    /**
     * 控制菜单显示隐藏
     *
     */
    private void showDrawerLayout() {
        if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.openDrawer(Gravity.LEFT);
        } else {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }
}
//javascript:if(window.allJS){allJS()}  调用js 方法~