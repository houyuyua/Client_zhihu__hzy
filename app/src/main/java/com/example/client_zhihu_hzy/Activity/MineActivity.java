package com.example.client_zhihu_hzy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.client_zhihu_hzy.R;

public class MineActivity extends AppCompatActivity implements View.OnClickListener{


    private Button button_homePage;
    private Button btniv_ques,btniv_ans,btniv_vote;
    private TextView textView_userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        initView();
        initEvent();





    }

    //初始化控件方法
    private void initView() {

        button_homePage = (Button)findViewById(R.id.bt_HomePage) ;
        btniv_ques = (Button)findViewById(R.id.iv_ques) ;
        btniv_ans = (Button)findViewById(R.id.iv_ans) ;
        btniv_vote = (Button)findViewById(R.id.iv_vote) ;


        textView_userName = (TextView) findViewById(R.id.tv_name);
    }


    //注册事件方法
    private void initEvent() {

        button_homePage.setOnClickListener(this);
        btniv_ques.setOnClickListener(this);
        btniv_ans.setOnClickListener(this);
        btniv_vote.setOnClickListener(this);
    }


    @Override
    //实现onClick方法
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_HomePage:
                Intent intentHome = new Intent(MineActivity.this, HomeActivity.class);
                startActivityForResult(intentHome,2);
                finish();
                break;

            case R.id.iv_ques:
                Intent intentQues = new Intent(MineActivity.this, myQuesActivity.class);
                startActivityForResult(intentQues,3);
                finish();
                break;

            case R.id.iv_vote:
                Intent intentVote = new Intent(MineActivity.this, myVoteActivity.class);
                startActivityForResult(intentVote,4);
                finish();
                break;

            case R.id.iv_ans:
                Intent intentAns = new Intent(MineActivity.this, myAnsActivity.class);
                startActivityForResult(intentAns,1);
                finish();
                break;

            default:
                break;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode ,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1://
                break;
            case 2://
                break;
            case 3://
                break;
            case 4://
                break;
            default:
                break;
        }
    }





}