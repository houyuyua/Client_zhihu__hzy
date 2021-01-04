package com.example.client_zhihu_hzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogin,btnReg;
    private EditText editName,editPwd;
    private String email;
    private String password;
    private SharedPreferences sp;
    private String result;

    private final int REQUEST_CODE=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnReg=(Button)findViewById(R.id.btnReg);
        editName=(EditText) findViewById(R.id.editName);
        editPwd=(EditText) findViewById(R.id.editPwd);
        sp = getSharedPreferences("config", 0);
        btnLogin.setOnClickListener(this);
        btnReg.setOnClickListener(this);
        email = editName.getText().toString().trim();

        password = editPwd.getText().toString().trim();
        editName.setText(email);
        this.editPwd.setText(password);
        ////传值没写完
        Intent intent =getIntent();
        String email = intent.getStringExtra("editEmail");
        String password = intent.getStringExtra("editPwd");
        editName.setText(email);
        editPwd.setText(password);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                email = editName.getText().toString().trim();
                password = editPwd.getText().toString().trim();
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(this, "账号或密码不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    new Thread(){


                        public void run(){
                            try {
                                //设置路径
                                String path="http://47.116.128.111:8080/user/login";
                                //创建URL对象
                                JSONObject json = new JSONObject();
                                try {
                                    json.put("email",email);
                                    json.put("password",password);

                                }catch (Exception ignore){
                                    Log.d("HttpThread1","异常");//将从服务器接收来的数据打印出来
                                }
                                Log.d("json->",json.toString());
                                PayHttpUtils payHttpUtils = new PayHttpUtils();
                                String res = payHttpUtils.post(path, json.toString());
                                Log.d("res",res);
                                //登录跳转
                                Intent intent=new Intent(MainActivity.this,homeActivity.class);
                                startActivityForResult(intent,REQUEST_CODE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        };
                    }.start();

                }
                break;
            case R.id.btnReg:
                //跳转到注册页面
                Intent intent=new Intent(this,MySecondActivityZc.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
            default:
                break;
        }

    }

}
