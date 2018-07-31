package com.example.xidian.myapplication1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xidian.adapter.IndicatorExpandableListAdapter;
import com.example.xidian.utils.Constant;
import com.example.xidian.utils.JavaScriptinterface;
import com.example.xidian.utils.MyApi;
import com.example.xidian.utils.Mylog;

public class Web_View extends AppCompatActivity implements View.OnClickListener {
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
    /*
    *  监听按钮事件
    * */
        callbackindex.setOnClickListener(this);
        Userbtn.setOnClickListener(this);
        peopleData.setOnClickListener(this);
        MyBtn.setOnClickListener(this);
        initView();
    }
    @SuppressLint("SetJavaScriptEnabled")
    /*
    * 跳转页面的方法
    * */
    private void GetenterPage(String pageNum) {
        Mylog.i(TAG,TAG+pageNum);
        switch(pageNum){
            case "00":case "10":case "20":case "30":
                webcontentbox.loadUrl(MyApi.WEBVIEW_URL+"/wheel_diameter.html");
                break;
            case "01" :case "11" :case "21" :case "31" :
                webcontentbox.loadUrl(MyApi.WEBVIEW_URL+"/wheel_setadd.html");
                break;
            case "02" :case "12" :case "22" :case "32" :
                webcontentbox.loadUrl(MyApi.WEBVIEW_URL+"/external_door.html");
                break;
            case "03" :case "13" :case "23" :case "33" :
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
                GetenterPage(String.valueOf(11));
                break;
            case R.id.peopleData:
                GetenterPage(String.valueOf(21));
                break;
            case R.id.Userbtn:
                GetenterPage(String.valueOf(31));
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
        drawerLayout=(DrawerLayout) findViewById(R.id.v4_drawerlayout);
        textView=(TextView) findViewById(R.id.v4_text);
        initDate();
    }
    //初始化数据
    private void initDate(){
        drawerLayout=(DrawerLayout) findViewById(R.id.v4_drawerlayout);
        final ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandable_list);
        final IndicatorExpandableListAdapter adapter = new IndicatorExpandableListAdapter(Constant.BOOKS, Constant.FIGURES);
        listView.setAdapter(adapter);
        // 清除默认的 Indicator
        listView.setGroupIndicator(null);
        //  设置分组项的点击监听事件
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d(TAG, "onGroupClick: groupPosition:" + groupPosition + ", id:" + id);
                boolean groupExpanded = parent.isGroupExpanded(groupPosition);
                adapter.setIndicatorState(groupPosition, groupExpanded);
                // 请务必返回 false，否则分组不会展开
                return false;
            }
        });
        //  设置子选项点击监听事件
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(Web_View.this, Constant.FIGURES[groupPosition][childPosition]+groupPosition+childPosition, Toast.LENGTH_SHORT).show();
                showDrawerLayout();
                String PageNum = ""+groupPosition+childPosition;
                GetenterPage(PageNum);
                return true;
            }
        });
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