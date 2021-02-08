package com.example.client_zhihu_hzy.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.client_zhihu_hzy.R;
import com.example.client_zhihu_hzy.ReturnData.editAnswerReturnData;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;




public class EditAnswerActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonSave,buttonDelete;
    private EditText editTextDesc;
    private String originAddress = "http://47.116.128.111:8080/answer/" ;
    private int answerId;
    private  String originAddressNew;//真正的地址

    private String desc;
    private String token;
    private editAnswerReturnData editAnswerReturnData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_answer);
        initView();
        initEvent();
        initDataIn();

    }




    //初始化控件方法
    private void initView() {
        buttonSave = (Button)findViewById(R.id.btSave);
        buttonDelete = (Button)findViewById(R.id.btDelete);
        editTextDesc = (EditText)findViewById(R.id.etEditDesc);

    }


    //注册事件方法
    private void initEvent() {
        buttonSave.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btSave:
                sendRequestWithSaveQuestion();
                break;
            case R.id.btDelete:
                sendRequestWithDeleteQuestion();
                break;

            default:
                break;
        }
    }

    //这里不仅包括网络地址的初始化，还包括传进title、desc
    private void initDataIn(){
        Intent intent = getIntent();
        answerId = intent.getIntExtra("extra_AnswerId", 1);
//        title = intent.getStringExtra("extraTitle");
        desc = intent.getStringExtra("extraDesc");
        Log.d("Edit66Activity","questionId : "+answerId);
        Log.d("Edit666Activity","desc : "+desc);
//        editTextTitle.setText(title);
        editTextDesc.setText(desc);
        StringBuffer Address = new StringBuffer(originAddress);
        Address.append(answerId);
        originAddressNew = new String(Address);

        SharedPreferences sp = getSharedPreferences("loginToken",0);
        token = sp.getString("token","");
    }


    //修改问题
    private void sendRequestWithSaveQuestion() {

//        title = editTextTitle.getText().toString().trim();
        desc = editTextDesc.getText().toString().trim();
        Log.d("EditQuestionActivity","desc : "+desc);

        if (desc.isEmpty()) {
            Toast.makeText(EditAnswerActivity.this, "回答详情不能为空！", Toast.LENGTH_LONG).show();
            return;
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建jSON格式数据
                MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                JSONObject json = new JSONObject();
                try {
//                    json.put("title", title);
                    json.put("content", desc);
//                    json.put("tag", " ");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //POST
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
                    Request request = new Request.Builder()
                            .url(originAddressNew)
                            .addHeader("authorization",token)
                            .put(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
    }





    //删除问题
    private void sendRequestWithDeleteQuestion(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(originAddressNew)
                            .addHeader("authorization",token)
                            .delete()
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("Edit666Activity","responseData is "+responseData);

                    parseJSONWithJSONObject(responseData);
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
    }



    private void parseJSONWithJSONObject(String jsonData){
        Gson gson = new Gson();
        editAnswerReturnData = gson.fromJson(jsonData,editAnswerReturnData.class);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(editAnswerReturnData.getMessage().equals("OK")) {
                    Toast.makeText(getApplicationContext(), "更新成功", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(EditQuestionActivity.this,HomeActivity.class);
//                    startActivity(intent);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(EditAnswerActivity.this,HomeActivity.class));
                        }
                    },100);


                }
            }
        });
    }




}
