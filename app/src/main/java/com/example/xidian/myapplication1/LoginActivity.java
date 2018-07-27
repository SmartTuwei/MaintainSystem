package com.example.xidian.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.example.xidian.utils.MyApi;
import com.example.xidian.utils.Mylog;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;
    private Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        loginbtn = (Button)findViewById(R.id.loginbtn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginbtn.setOnClickListener(this);
        //设置透明度
        loginbtn.getBackground().setAlpha(100);
    }
/*
*  点击事件
* */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginbtn:
                doGet();
                break;
        }
    }
    /*
    * 登录方法
    * */
    public void doGet(){
        String sname =  username.getText().toString();
        String spassword =  password.getText().toString();
        String url = MyApi.WEBVIEW_URL + "/login?username=" + sname +"&password="+spassword;
        OkHttpClient okHttpClient = new  OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        Call call = okHttpClient.newCall(request);
//      call.execute()
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage() );
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException  {
                final String res =   response.body().string();
                Log.e(TAG, "onResponse: " + res );
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject resdata = new JSONObject(res);
                            Log.e(TAG, "onResponse: " + resdata.get("status"));
                            String status = resdata.get("status").toString();
                            if(status.equals("true")){
                                Toast.makeText(LoginActivity.this,resdata.get("res").toString(),Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this,HomeMainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this,resdata.get("res").toString(),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}