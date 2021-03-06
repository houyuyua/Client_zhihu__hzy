package com.example.client_zhihu_hzy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.client_zhihu_hzy.adapter.guanzhuFragment;
import com.example.client_zhihu_hzy.adapter.rebangFragment;
import com.example.client_zhihu_hzy.adapter.tuijianFragment;
import com.google.android.material.internal.ViewUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity_sy extends AppCompatActivity implements View.OnClickListener {


    private List<Content> contentList = new ArrayList<>();
    private Button iv_fabuques;
    private RecyclerView recyclerView;
    public View view;
    private syReturnData syReturnData;
    private String originAddress = "http://47.116.128.111:8080/question";



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        sendRequestWithHttpURLConnection();
        ContentAdapter adapter = new ContentAdapter(contentList);
        recyclerView.setAdapter(adapter);

        switch (requestCode) {
            case 1:

                break;
            default:
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sy);
        initView();
        initEvent();
        sendRequestWithHttpURLConnection();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ContentAdapter adapter = new ContentAdapter(contentList);
        recyclerView.setAdapter(adapter);
    }


    //初始化控件方法
    private void initView() {
        iv_fabuques=(Button)iv_fabuques.findViewById(R.id.iv_fabuques);
        recyclerView = (RecyclerView) recyclerView.findViewById(R.id.rv_All);
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
                Intent intent = new Intent(MainActivity_sy.this,ques_send.class);
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

        List<questionData> list_questions = syReturnData.getData().getList_data();//问题列表提取成功



        Log.d("HomeFragment","jsonData is "+jsonData);
        Log.d("HomeFragment", "message is " + syReturnData.getMessage());

        Log.d("HomeFragment", "list_questions[0] =  " + list_questions.get(0).toString());//测试成功

        Log.d("HomeFragment", "list_questions[1].title =  " + list_questions.get(5).getTitle());

        Log.d("HomeFragment", "list_questions[1].content =  " + list_questions.get(5).getContent());



        for(int i=0;i<list_questions.size();i++){
            Content question = new Content(list_questions.get(i).getTitle(),R.drawable.fake,
                    "用户",list_questions.get(i).getContent(),"10 赞同","20评论");
            contentList.add(question);

        }



        //Handler(syReturn_data);
    }


}