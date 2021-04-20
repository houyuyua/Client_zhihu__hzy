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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.client_zhihu_hzy.R;
import com.example.client_zhihu_hzy.RecyclerViewAdapter.AnswerAdapterNew;
import com.example.client_zhihu_hzy.RecyclerViewAdapter.AnswerItemNew;
import com.example.client_zhihu_hzy.RecyclerViewAdapter.QuestionAdapter;
import com.example.client_zhihu_hzy.ReturnData.AnswersListReturnData;
import com.example.client_zhihu_hzy.ReturnData.PublishReturnData;
import com.example.client_zhihu_hzy.ReturnData.SingleAnswerData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OthersQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewTitle,textViewAgree,textViewStar,textViewUserName,textViewDescribe;
    private ImageButton imageButtonHead;
    private Button buttonWriteAnswer;
//    private ShineButton buttonHeart;
//    private ShineButton buttonStar;
    private Button buttonHowManyAnswer,buttonComment;
    private String originAddress = "http://47.116.128.111:8080/question/";
    private int questionId;
    private String originAddressNew;
    private PublishReturnData publishReturnData;//这个和发布问题后获取的数据一样，所以不再重复写

//更新
    private List<AnswerItemNew> answerItemList = new ArrayList<>();//改动了item类
    private AnswerAdapterNew mAnswerAdapterNew;
    private RecyclerView mAnswerRecyclerView;
    private String originAddressAnswer = "http://47.116.128.111:8080/answer?qid=";
    private String timeOrderAddress = "&size=25&orderby=time";
    private String NewAddress;//最终的目标地址
    private AnswersListReturnData answersListReturnData;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_question);
        initView();
        initEvent();
        initDataIn();
        sendRequestWithHttpURLConnection();
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
        textViewTitle = (TextView) findViewById(R.id.tv_Title);
//        textViewAgree = (TextView) findViewById(R.id.tvViews);
//        textViewStar = (TextView) findViewById(R.id.tv_star);
        textViewUserName = (TextView) findViewById(R.id.tv_UserName);
        textViewDescribe = (TextView) findViewById(R.id.tv_describe);
        imageButtonHead = (ImageButton) findViewById(R.id.ib_head);
        buttonWriteAnswer = (Button) findViewById(R.id.bt_WriterAnswer);
//        buttonHeart = (ShineButton) findViewById(R.id.bt_heart);
//        buttonStar = (ShineButton) findViewById(R.id.bt_star);
        buttonHowManyAnswer = (Button) findViewById(R.id.bt_HowManyAnswer);
//        buttonComment = (Button) findViewById(R.id.bt_comment);
        mAnswerRecyclerView = (RecyclerView) findViewById(R.id.rvAnswersList);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
    }


    //注册事件方法
    private void initEvent() {
        buttonWriteAnswer.setOnClickListener(this);
//        buttonHeart.setOnClickListener(this);
//        buttonStar.setOnClickListener(this);
        buttonHowManyAnswer.setOnClickListener(this);
//        buttonComment.setOnClickListener(this);
        //recyclerView设置布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAnswerRecyclerView.setLayoutManager(layoutManager);
    }


    //初始化传入该活动的数据
    private void initDataIn() {
        //传入问题的ID
        Intent intent = getIntent();
        questionId = intent.getIntExtra("extra_QuestionId",1);
        StringBuffer Address = new StringBuffer(originAddress);
        Address.append(questionId);
        originAddressNew = new String(Address);



        //回答列表
        StringBuffer AddressAnswer = new StringBuffer(originAddressAnswer);
        AddressAnswer.append(questionId);
        AddressAnswer.append(timeOrderAddress);//按发布时间排序
        NewAddress = new String(AddressAnswer);
        Log.d("AnswersListActivity","NewAddress is "+NewAddress);
    }
    /////////////////


    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.bt_heart :
//                break;
//
//            case R.id.bt_star :
//                break;

            case R.id.bt_WriterAnswer:
                //
               Intent intent = new Intent(OthersQuestionActivity.this,WriteAnswerActivity.class);
               intent.putExtra("extraQuestionId",questionId);
               intent.putExtra("extraTitle",publishReturnData.getData().getTitle());
               startActivity(intent);
               break;

//            case R.id.bt_comment :
//                break;

            case R.id.bt_HowManyAnswer:
//                Intent intent2 = new Intent(OthersQuestionActivity.this,AnswersListActivity.class);
//                intent2.putExtra("extraQuestionId",questionId);
//                intent2.putExtra("extraTitle",publishReturnData.getData().getTitle());
//                intent2.putExtra("extraAnswersCount",publishReturnData.getData().getAnswer_count());
//                intent2.putExtra("extraViewCount",publishReturnData.getData().getView_count());
//                startActivity(intent2);
                break;

            default:
                break;
        }
    }


    private void  sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(originAddressNew)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("QuestionActivity","responseData is "+responseData);
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
        publishReturnData = gson.fromJson(jsonData, PublishReturnData.class);
        Log.d("OtherQuestionActivity","publishReturnData is "+ publishReturnData.toString());


        //请求数据完毕，回UI线程
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewTitle.setText(publishReturnData.getData().getTitle());
                textViewDescribe.setText(publishReturnData.getData().getContent());
                textViewUserName.setText(publishReturnData.getData().getCreator().getName());
                buttonHowManyAnswer.setText(publishReturnData.getData().getAnswer_count()+"人回答了这个问题");
            }
        });
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
                    Toast.makeText(OthersQuestionActivity.this, "回答列表加载成功", Toast.LENGTH_LONG).show();
                }

            }
        });
    }









}