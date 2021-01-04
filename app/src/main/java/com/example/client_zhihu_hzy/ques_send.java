package com.example.client_zhihu_hzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ques_send extends AppCompatActivity {

    private Button ques_quxiao;
    private static final int RESULT_CODE=102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques_send);

        ques_quxiao =(Button)findViewById(R.id.ques_quxiao);
        ques_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ques_send.this,homeActivity.class);
                startActivityForResult(intent,RESULT_CODE);
            }
        });



    }





}