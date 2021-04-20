package com.example.client_zhihu_hzy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.client_zhihu_hzy.R;
import com.example.client_zhihu_hzy.RecyclerViewAdapter.HotAdapter;
import com.example.client_zhihu_hzy.RecyclerViewAdapter.HotItem;
import com.example.client_zhihu_hzy.RecyclerViewAdapter.QuestionAdapter;
import com.example.client_zhihu_hzy.RecyclerViewAdapter.QuestionItem;
import com.example.client_zhihu_hzy.ReturnData.HomeReturnData;
import com.example.client_zhihu_hzy.ReturnData.HotReturnData;
import com.example.client_zhihu_hzy.ReturnData.SingleHotQuestionData;
import com.example.client_zhihu_hzy.ReturnData.SingleQuestionData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class hot extends AppCompatActivity implements View.OnClickListener {

    private List<HotItem> hotItemList = new ArrayList<>();
//    private List<QuestionItem> questionItemList = new ArrayList<>();
    private Button buttonMine;
    private Button buttonPublish;
    private Button buttonHomePage;
    private Button buttonFollow;
    private RecyclerView mHotRecyclerView;
    private HotReturnData hotReturnData;
    private String originAddress = "http://47.116.128.111:8080/hot?&size=15";
    private HotAdapter mHotAdapter;
//    private QuestionAdapter mQuestionAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);
        initView();
        initEvent();


        sendRequestWithQuestionList();

//        mQuestionAdapter = new QuestionAdapter(questionItemList);
//        mHotRecyclerView.setAdapter(mQuestionAdapter);
        mHotAdapter = new HotAdapter(hotItemList);
        mHotRecyclerView.setAdapter(mHotAdapter);
        mHotRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));//划线


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


//                questionItemList.clear();
//                sendRequestWithQuestionList();
//                mQuestionAdapter = new QuestionAdapter(questionItemList);

                hotItemList.clear();
                sendRequestWithQuestionList();
                mHotAdapter = new HotAdapter(hotItemList);

                mHotAdapter.notifyDataSetChanged();
//                //模拟网络请求需要3000毫秒，请求完成，设置setRefreshing 为false
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

    }

    //初始化控件方法
    private void initView() {
        buttonMine = (Button) findViewById(R.id.bt_Mine);
        buttonPublish = (Button) findViewById(R.id.bt_Publish);
        mHotRecyclerView = (RecyclerView) findViewById(R.id.rv_hot);
        buttonHomePage = (Button) findViewById(R.id.bt_HomePage);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        buttonFollow = (Button) findViewById(R.id.zh_guanzhu);
    }


    //注册事件方法
    private void initEvent() {
        buttonMine.setOnClickListener(this);
        buttonPublish.setOnClickListener(this);
        buttonHomePage.setOnClickListener(this);
        buttonFollow.setOnClickListener(this);
        //recyclerView设置布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mHotRecyclerView.setLayoutManager(layoutManager);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_Mine:
                Intent intentMine = new Intent(hot.this, MineActivity.class);
                startActivityForResult(intentMine, 2);
                break;

            case R.id.zh_guanzhu:
                Intent intentHot = new Intent(hot.this, HomeActivity.class);
                startActivityForResult(intentHot, 3);
                break;

            case R.id.bt_Publish:
                Intent intentPublish = new Intent(hot.this, PublishActivity.class);
                startActivityForResult(intentPublish, 1);
                break;

            case R.id.bt_HomePage:
                break;

            default:
                break;
        }
    }


    private void sendRequestWithQuestionList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(originAddress)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("hot", "responseData is " + responseData);
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
    }

    //////////////////////////////
    private void parseJSONWithJSONObject(String jsonData) {

        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<HotReturnData>() {
        }.getType();
        hotReturnData = gson.fromJson(jsonData, type);
        Log.d("Hot", "hotReturnData is " + hotReturnData.toString());
        List<SingleHotQuestionData> questionsList = new ArrayList<>();
        questionsList = hotReturnData.getData().getList_data();//问题列表提取成功
        Log.d("ceshi", "jsonData 6666666666 " + questionsList);
        Log.d("ceshi", "jsonData is " + jsonData);
        Log.d("ceshi", "message is " + hotReturnData.getMessage());
//执行失败
        Log.d("ceshi", "list_questions " + hotReturnData.getData().getList_data().toString());

        for (int i = 0; i < questionsList.size(); i++) {


            HotItem questionItem = new HotItem(questionsList.get(i).getHeat(),questionsList.get(i).getAnswer().getCreator().getId() ,
                    questionsList.get(i).getId(), questionsList.get(i).getTitle(),
                    R.drawable.headimage,i+1);


            hotItemList.add(questionItem);
        }
//        for(int i = 0; i< questionsList.size(); i++){
//
//
//            QuestionItem questionItem = new QuestionItem(questionsList.get(i).getAnswer().getId() ,
//                    questionsList.get(i).getId(),questionsList.get(i).getTitle(),
//                    R.drawable.headimage,"hh",
//                    questionsList.get(i).getContent(),questionsList.get(i).getView_count(),
//                    questionsList.get(i).getAnswer_count());
//
//
//            questionItemList.add(questionItem);
//        }




//        Handler(HotReturnData);
    }

    private void Handler(HotReturnData hotReturnData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (hotReturnData.getMessage().equals("OK")) {
                    Toast.makeText(hot.this, "热榜加载成功", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    //    protected void onResume() {}


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1://publish
                break;
            case 2://mine
                break;
            case 3://follow
                break;
            default:
                break;
        }
    }
}
