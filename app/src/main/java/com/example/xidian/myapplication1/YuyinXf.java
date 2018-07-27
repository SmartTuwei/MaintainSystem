package com.example.xidian.myapplication1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class YuyinXf extends AppCompatActivity implements View.OnClickListener  {
    private Button btn_click;
    private static final String Tag = "YuyinXf";
    private EditText mResultText;
    private Button pause_btn;
    private MediaPlayer  mMediaPlayer;
    static ArrayList<Integer> songArrayList;
    static int songIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            pause();
            setContentView(R.layout.xfyuyin);
            btn_click = (Button) findViewById(R.id.clickbtn);
            pause_btn = (Button) findViewById(R.id.pause_btn);
            btnVoice();
            btn_click.setOnClickListener(this);
            pause_btn.setOnClickListener(this);
    }
    @Override

    /*
    *
    * 播放音乐;
    * */
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.clickbtn:
                Log.i(Tag, "clickbtn is clickbtn");
                if(!mMediaPlayer.isPlaying()){
                    mMediaPlayer.start();
                }
                break;
            case R.id.pause_btn:
                Log.i(Tag, "click 暂停");
                mMediaPlayer.pause();
            break;
        }
    };

    /*
    *
    *暂停播放
    *
    * */
    public void pause(){
        int [] arr = {1,5,3,6,9,7,8,5,3,23};
        for(int i=0;i<arr.length;i++){
            System.out.println(i);
        }
    };
    public void  btnVoice(){
        Log.i(Tag, "btnVoice is clickbtn");
        if (ContextCompat.checkSelfPermission(YuyinXf.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(YuyinXf.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            //GetArrayList();
            //initMediaPlayer();//初始化播放器 MediaPlayer
        }
    };
    //播放音乐
    public void initMediaPlayer() {
        try {
            //mMediaPlayer = MediaPlayer.create(this, R.raw.gbaqghls);
//            mMediaPlayer.setOnCompletionListener();
            //mMediaPlayer.setLooping(true);//设置为循环播放;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * 这是播放的list;
     * */
    static void GetArrayList(){

        songArrayList.add(R.raw.jt);
        Log.i(Tag, "ArrayList: "+songArrayList);
    };

    /*
    * 这是播放的监听实现类;
    * */
//    class CompletionListener implements MediaPlayer.OnCompletionListener {
//        @Override
//        public void onCompletion(MediaPlayer mp) {
//            nextsong();
//        }
//    };
//    /*
//     * 这是播放的方法;
//     * */
//    public void nextsong() {
//        if (songIndex < songArrayList.size() - 1) {
//            songIndex = songIndex + 1;
//            initMediaPlayer();
//        }
//        else {
//            songArrayList.clear();
//            songIndex = 0;
//        }
//    }
}

