package com.example.xidian.myapplication1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Ok_http extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "Ok_http";
    TextView tv_result;
    EditText ggggbbbbb;
    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ok_http_layout);
        Button btn_get = (Button) findViewById(R.id.btn_get);
        tv_result  = (TextView) findViewById(R.id.tv_result);
        ggggbbbbb = findViewById(R.id.ggggbbbbb);
        btn_get.setOnClickListener(this);
        tv_result.setOnClickListener(this);

    }
    @Override
    /*
     *
     * 播放音乐;
     * */
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_get:
                Log.i(TAG, "clickbtn is clickbtn");
                this.doGet(v);
                break;

        }
    };
    public void doGet(View view){
        OkHttpClient okHttpClient = new  OkHttpClient();
        Request.Builder builder = new Request.Builder();
        String url = ggggbbbbb.getText().toString();
        Log.i(TAG, "doGet: "+url);
        Request request = builder.get().url(url).build();
        Call call = okHttpClient.newCall(request);
//        call.execute()
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage() );
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response);
                final String res =   response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_result.setText(res);
                    }
                });
                Log.e(TAG, "onResponse: " + res);
            }
        });
    }
}