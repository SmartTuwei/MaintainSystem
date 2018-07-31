package com.example.xidian.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xidian.utils.Constant;
import com.example.xidian.adapter.IndicatorExpandableListAdapter;
import com.example.xidian.utils.Mylog;

public class HomeMainActivity extends AppCompatActivity {
    private static final String TAG = "HomeMainActivity";
    private ListView listView;
    private DrawerLayout drawerLayout;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        initView();
    }
    //侧滑菜单
    private void initView()
    {
        drawerLayout=(DrawerLayout) findViewById(R.id.v4_drawerlayout);
        textView=(TextView) findViewById(R.id.v4_text);
        initDate();
    }

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
                Toast.makeText(HomeMainActivity.this, Constant.FIGURES[groupPosition][childPosition]+groupPosition+childPosition, Toast.LENGTH_SHORT).show();
                showDrawerLayout();
                String PageNum = ""+groupPosition+childPosition;
                Mylog.i(TAG,PageNum);
                Getpage(PageNum);
                return true;
            }
        });
        /*
        * 默认打开侧滑菜单
        * */
        drawerLayout.openDrawer(Gravity.LEFT);
    }


    /**
     * 跳转页面的方法
     *
     */
    private void Getpage(String posotion_num) {
        Intent intent = new Intent(HomeMainActivity.this,Web_View.class);
        intent.putExtra("pageNum",posotion_num);
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
