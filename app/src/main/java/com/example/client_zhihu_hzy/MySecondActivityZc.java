package com.example.client_zhihu_hzy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.support.v4.app.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.PrivateKey;

public class MySecondActivityZc extends AppCompatActivity implements View.OnClickListener{
    private Button btnReg,btnRegQx,button_yz;
    private EditText editName,editPwd,editRepwd,editName1,editName11;
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
        setContentView(R.layout.activity_my_second_zc);
        button_yz=(Button)findViewById(R.id.button_yz);
        btnReg=(Button)findViewById(R.id.btnReg);
        btnRegQx=(Button)findViewById(R.id.btnRegQx);
        editName=(EditText) findViewById(R.id.editName);
        editName1=(EditText) findViewById(R.id.editName);
        editName11=(EditText) findViewById(R.id.editName);
        editPwd=(EditText) findViewById(R.id.editPwd);
        editRepwd=(EditText) findViewById(R.id.editRepwd);

        btnRegQx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MySecondActivityZc.this,MainActivity.class);
                startActivityForResult(intent,RESULT_CODE);
            }
        });
//////////这里没写完！！！！！！
        button_yz.setOnClickListener(new View.OnClickListener() {
            String url = "http://47.116.128.111:8080/user/register";
            @Override
            public void onClick(View v) {
                new HttpThread1(url, editName.getText().toString()).start();
            }
        });


    }

    @Override
    public void onClick(final View v) {
        new Thread(){public void run() {

            switch (v.getId()) {
                case R.id.btnReg:
                    email = editName.getText().toString().trim();
                    usernametrue = editName1.getText().toString().trim();
                    password = editPwd.getText().toString().trim();
                    pwd2 = editRepwd.getText().toString().trim();
                    email_yz = editName11.getText().toString().trim();
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
                            //?id="+username+"&password=" + pwd1+ "&email=" +email+"
                            //创建URL对象
                            URL url=new URL(path);
                            //创建一个HttpURLconnection对象
                            HttpURLConnection conn =(HttpURLConnection) url.openConnection();
                            //设置请求方法
                            conn.setRequestMethod("POST");
                            //设置请求超时时间
                            conn.setReadTimeout(5000);
                            //conn.setConnectTimeout(5000);
                            //Post方式不能设置缓存，需要手动设置
                            //conn.setUseCaches(false);
                            //准备要发送的数据
                            String data ="editName="+ URLEncoder.encode(email,"utf-8")+"&email_yzm="+URLEncoder.encode(email_yz,"utf-8")+"&usernametrue1="+URLEncoder.encode(usernametrue,"utf-8")+"&password="+URLEncoder.encode(password,"utf-8")+"&passwordRe="+URLEncoder.encode(pwd2,"utf-8");
                            //String data ="id="+ username +"&password="+ pwd1 +"&email="+ email+"";
                            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//使用的是表单请求类型
                            conn.setRequestProperty("Content-Length", data.length()+"");
                            conn.setDoInput(true);
                            conn.setDoOutput(true);
                            //连接
                            // conn.connect();
                            //获得返回的状态码
                            conn.getOutputStream().write(data.getBytes());
                            int code=conn.getResponseCode();
                            if(code==200){
                                //获得一个文件的输入流
                                InputStream inputStream= conn.getInputStream();
                                result = StringTools.readStream(inputStream);
                                //更新UI
                                showToast(result);
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    break;
            }


        };}.start();
    }
    public void showToast(final String content){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if(result.equals("success")){
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    setResult(RESULT_CODE, intent);
                    finish();
                }

            }
        });
    }


}
