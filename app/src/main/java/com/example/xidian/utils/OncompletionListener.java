package com.example.xidian.utils;

import android.media.MediaPlayer;
import android.util.Log;

public class OncompletionListener {
    private static final String Tag = "OncompletionListener";
    public static MediaPlayer.OnCompletionListener Listener(){
        return new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(Tag, "播放完毕");
            }
        };
    }
}