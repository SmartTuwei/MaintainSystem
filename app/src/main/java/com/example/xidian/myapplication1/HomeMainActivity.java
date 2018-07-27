package com.example.xidian.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xidian.utils.MyApi;
import com.example.xidian.utils.Mylog;

import java.util.ArrayList;
import java.util.List;

public class HomeMainActivity extends AppCompatActivity {
    private static final String Tag = "HomeMainActivity";
    private ListView listView;
    private DrawerLayout drawerLayout;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        initView();
    }

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
         drawerLayout.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.remove_item:
                break;
            case R.id.add_item:
                break;
            case R.id.remove_item1:
                break;
            default:
                Mylog.i(Tag,"666");
                break;
        }
        return true;
    }

    /**
     * 跳转页面的方法
     *
     */
    private void Getpage(int posotion_num) {
        Intent intent = new Intent(HomeMainActivity.this,Web_View.class);
        String Datenum =  String.valueOf(posotion_num);
        intent.putExtra("pageNum",Datenum);
        startActivity(intent);
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
