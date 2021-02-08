package com.example.client_zhihu_hzy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.client_zhihu_hzy.R;
import com.example.client_zhihu_hzy.ReturnData.RegisterReturnData;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Looper;
import android.support.v4.app.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.PrivateKey;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnReg,btnRegQx,button_yz;
    private EditText editEmail,editPwd,editRepwd,editName,editYzm;
    private String result;
    private String email;
    private String usernametrue;
    private String email_yz;
    private String password;
    private String pwd2;

    private static final int RESULT_CODE=102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button_yz=(Button)findViewById(R.id.button_yz);
        btnReg=(Button)findViewById(R.id.btnReg);
        btnRegQx=(Button)findViewById(R.id.btnRegQx);
        editEmail=(EditText) findViewById(R.id.editEmail);
        editName=(EditText) findViewById(R.id.editName);
        editYzm=(EditText) findViewById(R.id.editYzm);
        editPwd=(EditText) findViewById(R.id.editPwd);
        editRepwd=(EditText) findViewById(R.id.editRepwd);

        btnRegQx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivityForResult(intent,RESULT_CODE);
            }
        });

        button_yz.setOnClickListener(new View.OnClickListener() {
            String url = "http://47.116.128.111:8080/user/verify";
            @Override
            public void onClick(View v) {
                new HttpThread1(url, editEmail.getText().toString()).start();
                Toast.makeText(getApplicationContext(), "验证码已发送！", Toast.LENGTH_SHORT).show();
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){public void run() {

                    switch (v.getId()) {
                        case R.id.btnReg:
                            email = editEmail.getText().toString().trim();
                            usernametrue = editName.getText().toString().trim();
                            password = editPwd.getText().toString().trim();
                            pwd2 = editRepwd.getText().toString().trim();
                            email_yz = editYzm.getText().toString().trim();
                            //System.out.println("email:"+email);

                            if(!password.equals(pwd2)){
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "两次输入密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else {
                                try {
                                    //设置路径
                                    String path="http://47.116.128.111:8080/user/register";

                                    JSONObject json = new JSONObject();
                                    try {
                                        json.put("email",email);
                                        json.put("verification_code",email_yz);
                                        json.put("password",password);
                                        json.put("re_password",pwd2);
                                        json.put("name",usernametrue);
                                    }catch (Exception ignore){
                                        Log.d("HttpThread1","异常");//将从服务器接收来的数据打印出来
                                    }
                                    Log.d("json->",json.toString());
                                    PayHttpUtils payHttpUtils = new PayHttpUtils();
                                    String res = payHttpUtils.post(path, json.toString());
                                    Log.d("res",res);
                                    Looper.prepare();

                                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                                    String email1 = editEmail.getText().toString();
                                    String password1 = editPwd.getText().toString();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    //传值有问题
                                    intent.putExtra("editEmail",email1);
                                    intent.putExtra("editPwd",password1);
                                    //设置结果码和返回的activity
                                    startActivity(intent);
                                    Looper.loop();//增加部分
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();}
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        default:
                            break;
                    }


                };}.start();

            }
        });

    }

    @Override
    public void onClick(final View v) {

    }






}

