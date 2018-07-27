package com.example.xidian.myapplication1;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashMainActivity extends AppCompatActivity {
    private TextView slpash_version;
    private int mLocalVersionCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);*/
        setContentView(R.layout.splash_activity);
        TextView tv = findViewById(R.id.slpash_logo);
        slpash_version = findViewById(R.id.slpash_version);

//        初始化方法
        initUi();
        //初始化数据;
        initData();
        initAnimation();
        timer1();
    }
    /**
     *
     */
    public void timer1() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                enterHome();
            }
        }, 3000);// 设定指定的时间time,此处为2000毫秒
    }

    private void initData() {
        //1,应用版本名称
        slpash_version.setText("版本名称:"+getVersionName());
        //检测(本地版本号和服务器版本号比对)是否有更新,如果有更新,提示用户下载(member)
        //2,获取本地版本号
        mLocalVersionCode = getVersionCode();
    }
    /**
     * 获取版本名称:清单文件中
     * @return	应用版本名称	返回null代表异常
     */
    private String getVersionName() {
        //1,包管理者对象packageManager
        PackageManager pm = getPackageManager();
        //2,从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号),传0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            //3,获取版本名称
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 返回版本号
     * @return
     * 非0 则代表获取成功
     */
    private int getVersionCode() {
        //1,包管理者对象packageManager
        PackageManager pm = getPackageManager();
        //2,从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号),传0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            //3,获取版本名称
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }




    public void initUi(){

    }
    //获取数据

    //初始化动画;
    public void initAnimation(){

    }

    /**
     * 进入应用程序主界面
     */
    protected void enterHome() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        //在开启一个新的界面后,将导航界面关闭(导航界面只可见一次)
        finish();
    }
}
