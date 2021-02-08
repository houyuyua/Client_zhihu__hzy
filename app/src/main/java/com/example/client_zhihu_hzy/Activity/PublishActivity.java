package com.example.client_zhihu_hzy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.client_zhihu_hzy.R;
import com.example.client_zhihu_hzy.ReturnData.PublishReturnData;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PublishActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_publishTitle,editText_publishDescribe;
    private Button button_checkPublish,ques_quxiao;
    private PublishReturnData publishReturnData;
    private String originAddress = "http://47.116.128.111:8080/question";
    private static final int RESULT_CODE=102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        initView();
        initEvent();
        ques_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(PublishActivity.this,HomeActivity.class);
                startActivityForResult(intent,RESULT_CODE);
            }
        });
    }


    //初始化控件方法
    private void initView() {
        editText_publishTitle = (EditText) findViewById(R.id.et_publishTitle);
        editText_publishDescribe = (EditText) findViewById(R.id.et_publishDescribe);
        button_checkPublish = (Button) findViewById(R.id.bt_checkPublish);
        ques_quxiao =(Button)findViewById(R.id.ques_quxiao);
    }


    //注册事件方法
    private void initEvent() {
        button_checkPublish.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_checkPublish:
                sendRequestWithHttpURLConnection();
                break;

            default:
                break;
        }
    }


    private void sendRequestWithHttpURLConnection() {

        String title = editText_publishTitle.getText().toString().trim();
        String content = editText_publishDescribe.getText().toString().trim();

        if (title.isEmpty()) {
            Toast.makeText(PublishActivity.this, "问题不能为空！", Toast.LENGTH_LONG).show();
            return;
        } else if (content.isEmpty()) {
            Toast.makeText(PublishActivity.this, "问题描述不能为空！", Toast.LENGTH_LONG).show();
            return;
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建jSON格式数据
                MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                JSONObject json = new JSONObject();
                try {
                    json.put("title", title);
                    json.put("content", content);
                    json.put("tag", " ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //POST
                try {
                    SharedPreferences sp = getSharedPreferences("loginToken", 0);
                    String token = sp.getString("token", "");
//                    Log.d("PublishActivity","token is "+token);

                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
                    Request request = new Request.Builder()
                            .url(originAddress)
                            .addHeader("authorization", token)
                            .post(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
//                  showResponse(responseData);
                    parseJSONWithJSONObject(responseData);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();

    }


    private void parseJSONWithJSONObject(String jsonData){

        Gson gson = new Gson();
        publishReturnData = gson.fromJson(jsonData, PublishReturnData.class);
        Log.d("PublishActivity","publishReturnData is "+publishReturnData.toString());

        //切回UI线程做后处理
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (publishReturnData.getMessage().equals("OK")) {
                    Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PublishActivity.this, HomeActivity.class);
                    setResult(RESULT_OK, intent);
                    //       finish();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(PublishActivity.this, HomeActivity.class));
                        }
                    }, 500);

                }else {
                    Toast.makeText(getApplicationContext(), "发布失败，请检查网络错误", Toast.LENGTH_LONG).show();
                }
            }
        });
    }





}