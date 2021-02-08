package com.example.client_zhihu_hzy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.client_zhihu_hzy.R;
import com.example.client_zhihu_hzy.ReturnData.PublishReturnData;
import com.example.client_zhihu_hzy.ReturnData.WriteAnswerReturnData;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyAnswerActivity extends AppCompatActivity implements View.OnClickListener  {


    private TextView textViewTitle,textViewUserName,textViewDescribe;
    private ImageButton imageButtonHead;
    private Button buttonEditAnswwer,buttonHowManyAgree;
    private String originAddress = "http://47.116.128.111:8080/answer/" ;
    private int answerId;
//    private PublishReturnData publishReturnData;//这个和发布问题后获取的数据一样，所以不再重复写
    private WriteAnswerReturnData writeAnswerReturnData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_answer);
        initView();
        initEvent();
        initDataIn();
        sendRequestWithHttpURLConnection();
    }


    //初始化控件方法
    private void initView() {
        textViewTitle = (TextView) findViewById(R.id.tv_Title);
        textViewUserName = (TextView) findViewById(R.id.tv_UserName);
        textViewDescribe = (TextView) findViewById(R.id.tv_describe);
        imageButtonHead = (ImageButton) findViewById(R.id.ib_head);
        buttonEditAnswwer = (Button) findViewById(R.id.bt_EditAnswer);
        buttonHowManyAgree = (Button) findViewById(R.id.bt_HowManyAgree);

    }


    //注册事件方法
    private void initEvent() {
        buttonEditAnswwer.setOnClickListener(this);
//        buttonHowManyAgree.setOnClickListener(this);
    }


    //初始化传入该活动的数据
    private void initDataIn() {
        //传入问题的ID
        Intent intent = getIntent();
        answerId = intent.getIntExtra("extra_AnswerId", 1);
        Log.d("QuestionActivity", "extra_AnswerId is " + answerId);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_EditAnswer:
                Intent intent = new Intent(MyAnswerActivity.this, EditAnswerActivity.class);
                intent.putExtra("extra_AnswerId", answerId);
//                intent.putExtra("extraTitle", writeAnswerReturnData.getData().getContent());
                intent.putExtra("extraDesc", writeAnswerReturnData.getData().getContent());
                startActivity(intent);
                break;

//            case R.id.bt_HowManyAgree:
//                Intent intent2 = new Intent(MyQuestionActivity.this,AnswersListActivity.class);
//                intent2.putExtra("extraQuestionId",questionId);
//                intent2.putExtra("extraTitle",publishReturnData.getData().getTitle());
////                intent2.putExtra("extraAnswersCount",publishReturnData.getData().getAnswersCount());
////                intent2.putExtra("extraViewCount",publishReturnData.getData().getViewCount());
//                startActivity(intent2);
//                break;

            default:
                break;
        }
    }


    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StringBuffer Address = new StringBuffer(originAddress);
                    Address.append(answerId);
                    String originAddressNew = new String(Address);
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(originAddressNew)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("answerIdActivity", "responseData is " + responseData);
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
    }


    private void parseJSONWithJSONObject(String jsonData) {
        Gson gson = new Gson();
        writeAnswerReturnData = gson.fromJson(jsonData, WriteAnswerReturnData.class);
        Log.d("MyQuestionActivity", "publishReturnData is  " + writeAnswerReturnData.toString());
        //切回IU线程
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewTitle.setText("回答详情");
                textViewDescribe.setText(writeAnswerReturnData.getData().getContent());
                textViewUserName.setText(writeAnswerReturnData.getData().getCreator().getName());
                buttonHowManyAgree.setText(writeAnswerReturnData.getData().getUpvote_count()+"人赞同了这个问题");
            }
        });
    }




}