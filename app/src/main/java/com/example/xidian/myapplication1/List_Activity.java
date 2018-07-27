package com.example.xidian.myapplication1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class  List_Activity extends AppCompatActivity {
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new MyListAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();//获得菜单转换器
        inflater.inflate(R.menu.homemenu, menu);//将Menu资源转换成系统菜单
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                break;
            case R.id.remove_item:
                Toast.makeText(this, "you delect item", Toast.LENGTH_LONG).show();
                break;
                default:
        };
            return true;
    };

    /*
      * Dialog
      *
    */

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50000;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv ;
            if (convertView == null) {
                tv = new TextView(List_Activity.this);
                System.out.println(""+position);
            }else{
                System.out.println(""+position);
                tv = (TextView) convertView;
            }
            tv.setText(""+position);
            return tv;
        }

    }
}