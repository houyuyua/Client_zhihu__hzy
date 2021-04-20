package com.example.client_zhihu_hzy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.client_zhihu_hzy.R;
import com.example.client_zhihu_hzy.RecyclerViewAdapter.QuestionAdapter;
import com.example.client_zhihu_hzy.RecyclerViewAdapter.QuestionItem;
import com.example.client_zhihu_hzy.ReturnData.HomeReturnData;
import com.example.client_zhihu_hzy.ReturnData.SingleQuestionData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class myQuesActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button_MinePage;
    private List<QuestionItem> questionItemList = new ArrayList<>();
    private RecyclerView mQuestionRecyclerView;
    private HomeReturnData homeReturnData;
    private String originAddress = "http://47.116.128.111:8080/question";
    private QuestionAdapter mQuestionAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ques);
        initView();
        initEvent();



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        sendRequestWithQuestionList();

        mQuestionAdapter = new QuestionAdapter(questionItemList);
        mQuestionRecyclerView.setAdapter(mQuestionAdapter);
        mQuestionRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));//划线


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {




                questionItemList.clear();
                sendRequestWithQuestionList();
                mQuestionAdapter = new QuestionAdapter(questionItemList);

                mQuestionAdapter.notifyDataSetChanged();
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
        mQuestionRecyclerView = (RecyclerView) findViewById(R.id.rv_All);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
    }


    //注册事件方法
    private void initEvent() {
        button_MinePage.setOnClickListener(this);
        //button_homePaglerView设置布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mQuestionRecyclerView.setLayoutManager(layoutManager);
    }


    @Override
    //实现onClick方法
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_Mine:
                Intent intentHome = new Intent( myQuesActivity.this, MineActivity.class);
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
                            .url(originAddress)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("HomeActivity","responseData is "+responseData);
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
        java.lang.reflect.Type type = new TypeToken<HomeReturnData>() {}.getType();
        homeReturnData = gson.fromJson(jsonData, type);
        Log.d("HomeActivity", "homeReturnData is " + homeReturnData.toString());
        List<SingleQuestionData> questionsList = new ArrayList<>();
        questionsList = homeReturnData.getData().getList_data();//问题列表提取成功
        Log.d("ceshi","jsonData 6666666666 "+questionsList);
        Log.d("ceshi","jsonData is "+jsonData);
        Log.d("ceshi", "message is " + homeReturnData.getMessage());
//执行失败
        Log.d("ceshi", "list_questions " + homeReturnData.getData().getList_data().toString());

        for(int i = 0; i< questionsList.size(); i++){
            String url ="https://img2.woyaogexing.com/2021/01/20/6f1c3222b3c547e6bea6a1c39724bf41!400x400.jpeg";
            if(!(questionsList.get(i).getCreator().getAvatar_url().equals(""))){
                url = questionsList.get(i).getCreator().getAvatar_url();}
            System.out.println(url);
            Bitmap tmpBitmap = null;
            try {
                InputStream is = new java.net.URL(url).openStream();
                tmpBitmap = BitmapFactory.decodeStream(is);
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("KK下载图片", e.getMessage());
            }
            if(questionsList.get(i).getCreator().getId()==7){
                QuestionItem questionItem = new QuestionItem(questionsList.get(i).getCreator().getId() ,
                        questionsList.get(i).getId(),questionsList.get(i).getTitle(),
                        tmpBitmap,questionsList.get(i).getCreator().getName(),
                        questionsList.get(i).getContent(),questionsList.get(i).getView_count(),
                        questionsList.get(i).getAnswer_count());


                questionItemList.add(questionItem);}
        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(homeReturnData.getMessage().equals("OK")) {
//                    textViewTitle.setText(questionTitle);
//                    textViewViews.setText(viewCount+"浏览");
//                    textViewAnswers.setText(answersCount+"回答");
                    Toast.makeText(myQuesActivity.this, "问题列表加载成功", Toast.LENGTH_LONG).show();
                }

            }
        });
        // Handler(homeReturn_data);
    }

//    private void Handler(HomeReturnData homeReturnData){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if(homeReturnData.getMessage().equals("OK")) {
//                    Toast.makeText(HomeActivity.this, "问题列表加载成功", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });
//    }

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