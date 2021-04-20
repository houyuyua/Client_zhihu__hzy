package com.example.client_zhihu_hzy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.client_zhihu_hzy.R;
import com.example.client_zhihu_hzy.RecyclerViewAdapter.AnswerAdapterNew;
import com.example.client_zhihu_hzy.RecyclerViewAdapter.AnswerItemNew;
import com.example.client_zhihu_hzy.ReturnData.AnswersListReturnData;
import com.example.client_zhihu_hzy.ReturnData.SingleAnswerData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class myVoteActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button_MinePage;


    private String originAddressAnswer = "http://47.116.128.111:8080/answer?voteby=";
    private String timeOrderAddress = "&size=25&orderby=time";
    private String NewAddress;//最终的目标地址
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<AnswerItemNew> answerItemList = new ArrayList<>();//改动了item类
    private AnswerAdapterNew mAnswerAdapterNew;
    private RecyclerView mAnswerRecyclerView;
    private String token;
    private AnswersListReturnData answersListReturnData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vote);

        initView();
        initEvent();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        sendRequestWithQuestionList();

        mAnswerAdapterNew = new AnswerAdapterNew(answerItemList);
        mAnswerRecyclerView.setAdapter(mAnswerAdapterNew);
        mAnswerRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));//划线

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {




                answerItemList.clear();
                sendRequestWithQuestionList();
                mAnswerAdapterNew = new AnswerAdapterNew(answerItemList);

                mAnswerAdapterNew.notifyDataSetChanged();
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
        button_MinePage = (Button)findViewById(R.id.bt_Mine) ;
//        buttonComment = (Button) findViewById(R.id.bt_comment);
        mAnswerRecyclerView = (RecyclerView) findViewById(R.id.rv_All);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
    }


    //注册事件方法
    private void initEvent() {
        button_MinePage.setOnClickListener(this);

        //recyclerView设置布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAnswerRecyclerView.setLayoutManager(layoutManager);
        //token
        SharedPreferences sp = getSharedPreferences("loginToken",0);
        token = sp.getString("token","");
        int uid = sp.getInt("uid",7);

        //IP初始化
        StringBuffer Address = new StringBuffer(originAddressAnswer);
        Address.append(uid);
        Address.append(timeOrderAddress);
        NewAddress = new String(Address);
    }




    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.bt_heart :
//                break;
//
//            case R.id.bt_star :
//                break;



//            case R.id.bt_comment :
//                break;

            case R.id.bt_Mine:
                Intent intentHome = new Intent( myVoteActivity.this, MineActivity.class);
                startActivityForResult(intentHome,1);

                break;


            default:
                break;
        }
    }


    private void sendRequestWithQuestionList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(NewAddress)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("AnswersListActivity","responseData is "+responseData);
                    parseJSONWithGson(responseData);
                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
    }

    private void parseJSONWithGson(String jsonData){

        Gson gson = new Gson();
        Type type = new TypeToken<AnswersListReturnData>() {}.getType();
        answersListReturnData = gson.fromJson(jsonData, type);
        Log.d("AnswersListActivity", "answersListReturnData is " + answersListReturnData.toString());
        List<SingleAnswerData> answersList = new ArrayList<>();
        answersList = answersListReturnData.getData().getList_data();//问题列表提取成功
        Log.d("answersList", "answersList is " + answersList);
//        List<SingleAnswerData> answersList = answersListReturnData.getData();//回答列表提取成功

        for(int i = 0; i< answersList.size(); i++){

                AnswerItemNew answerItem = new AnswerItemNew(answersList.get(i).getCreator().getId(),
                        answersList.get(i).getCreator().getName(),
                        R.drawable.hzy, answersList.get(i).getContent(), 10, 10, answersList.get(i).getId());//content为后端的回答，等于answer
                answerItemList.add(answerItem);
                Log.d("answerItemList", "answersList is " + answerItemList);


        }



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(answersListReturnData.getMessage().equals("OK")) {
//                    textViewTitle.setText(questionTitle);
//                    textViewViews.setText(viewCount+"浏览");
//                    textViewAnswers.setText(answersCount+"回答");
                    Toast.makeText(myVoteActivity.this, "点赞列表加载成功", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode ,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1://publish
                break;

            default:
                break;
        }
    }



}