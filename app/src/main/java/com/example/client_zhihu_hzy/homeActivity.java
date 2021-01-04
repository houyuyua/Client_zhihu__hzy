package com.example.client_zhihu_hzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class homeActivity extends AppCompatActivity implements View.OnClickListener {


    private List<Content> contentList = new ArrayList<>();
    private Button iv_fabuques;
    private RecyclerView recyclerView;
    public View view;
    private syReturnData syReturnData;
    private String originAddress = "http://47.116.128.111:8080/question";


//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        sendRequestWithHttpURLConnection();
//        ContentAdapter adapter = new ContentAdapter(contentList);
//        recyclerView.setAdapter(adapter);
//
//        switch (requestCode) {
//            case 1:
//                break;
//            default:
//                break;
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sendRequestWithHttpURLConnection();
        initView();
        initEvent();
//        sendRequestWithHttpURLConnection();
        
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ContentAdapter adapter = new ContentAdapter(contentList);
        recyclerView.setAdapter(adapter);

    }


    //初始化控件方法
    private void initView() {
        iv_fabuques=(Button)findViewById(R.id.iv_fabuques);
        recyclerView = (RecyclerView)findViewById(R.id.rv_All);
    }

    //注册事件方法
    private void initEvent() {
        iv_fabuques.setOnClickListener(this);

        //注册recycleView
//        initContents();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_fabuques :
                Intent intent = new Intent(homeActivity.this,ques_send.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    private void sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    okhttp3.OkHttpClient client = new OkHttpClient();
                    okhttp3.Request request = new Request.Builder()
                            .url(originAddress)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    //解析jason数据
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
        //解析Jason数组
        java.lang.reflect.Type type = new TypeToken<syReturnData>() {}.getType();
        syReturnData = gson.fromJson(jsonData, type);
        //提取问题列表
        List<questionData> list_questions = syReturnData.getData().getList_data();


        Log.d("ceshi","jsonData is "+jsonData);
        Log.d("ceshi", "message is " + syReturnData.getMessage());
//执行失败
        Log.d("ceshi", "list_questions " + syReturnData.getData().getList_data().toString());
        Log.d("ceshi", "list_questions[0] =  " + list_questions.get(0).toString());
        Log.d("ceshi", "list_questions[1].title =  " + list_questions.get(1).getTitle());
        Log.d("ceshi", "list_questions[1].content =  " + list_questions.get(2).getContent());


        for(int i=0;i<list_questions.size();i++){
            Content question = new Content(list_questions.get(i).getTitle(),R.drawable.fake,
                    "用户",list_questions.get(i).getContent(),"10 赞同","20评论");
            Log.d("6666", "666666666666666" );
            contentList.add(question);

        }



        //Handler(syReturn_data);
    }
    private void Handler(syReturnData syReturnData){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(syReturnData.getMessage().equals("OK")) {
                    Toast.makeText(homeActivity.this, "问题列表加载成功", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }


}