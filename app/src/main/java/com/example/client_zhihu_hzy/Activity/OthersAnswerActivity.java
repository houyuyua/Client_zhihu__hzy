package com.example.client_zhihu_hzy.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.client_zhihu_hzy.R;
import com.example.client_zhihu_hzy.ReturnData.WriteAnswerReturnData;
import com.google.gson.Gson;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OthersAnswerActivity  extends AppCompatActivity implements View.OnClickListener {


    private TextView textViewTitle,textViewUserName,textViewDescribe;
    private ImageButton imageButtonHead;
    private Button buttonShouCang,buttonHowManyAgree;
    private String originAddress = "http://47.116.128.111:8080/answer/" ;
    private int answerId;
    //    private PublishReturnData publishReturnData;//这个和发布问题后获取的数据一样，所以不再重复写
    private WriteAnswerReturnData writeAnswerReturnData;


    private ToggleButton toggleButtonAgree;
    private ToggleButton toggleButtonDisagree;

    private String answer;


    private String agreeOriginAddress = "http://47.116.128.111:8080/vote/answer/";
    private String agreeNewAddress ;
    private String disagreeNewAddress ;//反对


    private int supportNUm;
    private int unsupportNUm;

    private String token;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_answer);
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
        buttonShouCang = (Button) findViewById(R.id.bt_EditAnswer);
        buttonHowManyAgree = (Button) findViewById(R.id.bt_HowManyAgree);

        toggleButtonAgree=(ToggleButton) findViewById(R.id.ToggleBtAgree);
        toggleButtonDisagree = (ToggleButton) findViewById(R.id.ToggleBtDisagree);


    }


    //注册事件方法
    private void initEvent() {
//        buttonEditAnswwer.setOnClickListener(this);
//        buttonHowManyAgree.setOnClickListener(this);

        //点赞的监听
        toggleButtonAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){//开
                    toggleButtonDisagree.setVisibility(View.VISIBLE);
                    supportNUm++;
                    toggleButtonAgree.setTextOn("赞同"+String.valueOf(supportNUm));
                    buttonHowManyAgree.setText( supportNUm+"人赞同了这个问题");
                    sendRequestForAgree();


                }
                else{//关
                    toggleButtonDisagree.setVisibility(View.VISIBLE);
                    supportNUm--;
                    toggleButtonAgree.setTextOff("赞同"+String.valueOf(supportNUm));
                    buttonHowManyAgree.setText( supportNUm+"人赞同了这个问题");
                    sendRequestForCancelAgree();
                }
            }
        });


        //反对的监听
        toggleButtonDisagree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){//开
                    toggleButtonAgree.setVisibility(View.VISIBLE);
                    unsupportNUm = 1;
                    toggleButtonDisagree.setTextOff("反对"+String.valueOf(unsupportNUm));
                    sendRequestForDisagree();

                }
                else{//关
                    toggleButtonAgree.setVisibility(View.VISIBLE);

                    toggleButtonDisagree.setTextOff("反对");
                    sendRequestForCancelDisagree();
                }
            }
        });
    }


    //初始化传入该活动的数据
    private void initDataIn() {
        //传入问题的ID
        Intent intent = getIntent();
        answerId = intent.getIntExtra("extra_AnswerId", 1);
        Log.d("QuestionActivity", "extra_AnswerId is " + answerId);
        SharedPreferences sp = getSharedPreferences("loginToken", 0);
        token = sp.getString("token", "");


        //点赞
        StringBuffer Address2 = new StringBuffer(agreeOriginAddress);
        Address2.append(answerId);
        Address2.append("?s=1");
        agreeNewAddress = new String(Address2);

        //踩
        StringBuffer Address3 = new StringBuffer(agreeOriginAddress);
        Address3.append(answerId);
        Address3.append("?s=2");
        disagreeNewAddress = new String(Address3);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_EditAnswer:
//                Intent intent = new Intent(OthersAnswerActivity.this, EditAnswerActivity.class);
//                intent.putExtra("extra_AnswerId", answerId);
////                intent.putExtra("extraTitle", writeAnswerReturnData.getData().getContent());
//                intent.putExtra("extraDesc", writeAnswerReturnData.getData().getContent());
//                startActivity(intent);
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
                            .addHeader("authorization", token)
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
//                buttonHowManyAgree.setText(writeAnswerReturnData.getData().getUpvote_count()+"人赞同了这个问题");

                buttonHowManyAgree.setText( supportNUm+"人赞同了这个问题");
                supportNUm=writeAnswerReturnData.getData().getUpvote_count();
                toggleButtonAgree.setTextOff("赞同"+String.valueOf(supportNUm));
            }
        });
    }


    //点赞请求
    private void sendRequestForAgree() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //post
                try {
                    MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                    JSONObject json = new JSONObject();
                    RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(agreeNewAddress)
                            .addHeader("authorization", token)
                            .put(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("6666666", "agreeNewAddress " + agreeNewAddress);
                    Log.d("666666", "agreeReturnData is " + token);
                    Log.d("AnswerActivity", "AgreeReturnData is " + responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();

    }



    //点赞取消
    private void  sendRequestForCancelAgree() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //post
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(agreeNewAddress)
                            .addHeader("authorization", token)
                            .delete()
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("AnswerActivity", "AgreeReturnData is " + responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();

    }




    //反对请求
    private void sendRequestForDisagree() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //post
                try {
                    MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                    JSONObject json = new JSONObject();
                    RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(disagreeNewAddress)
                            .addHeader("authorization", token)
                            .put(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("AnswerActivity", "DisagreeReturnData is " + responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();

    }



    //点赞取消
    private void  sendRequestForCancelDisagree() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //post
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(disagreeNewAddress)
                            .addHeader("authorization", token)
                            .delete()
                            .build();

                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("AnswerActivity", "DisagreeReturnData is " + responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();

    }
}
