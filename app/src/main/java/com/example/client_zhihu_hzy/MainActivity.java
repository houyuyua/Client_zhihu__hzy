package com.example.client_zhihu_hzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        String email = sp.getString("email", "");
        String password = sp.getString("password", "");
        editName.setText(email);
        this.editPwd.setText(password);

    }
        @Override
        protected void onActivityResult(int requestCode,int resultCode,Intent data){
            super.onActivityResult(requestCode,resultCode,data);
            if(requestCode==REQUEST_CODE&&resultCode==REQUEST_CODE){
                editName.setText(data.getStringExtra("email"));
                editPwd.setText(data.getStringExtra("password"));
            }
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
                                URL url=new URL(path);
                                //创建一个HttpURLConnection对象
                                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                                //设置请求方法
                                conn.setRequestMethod("POST");
                                //设置请求超时时间
                                conn.setReadTimeout(5000);
                                conn.setConnectTimeout(5000);

                                //Post方式不能设置缓存，需要手动设置
                                conn.setUseCaches(false);
                                //设置我们的请求数据
                                String data="email="+email+"&password="+password;
                                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//使用的是表单请求类型
                                conn.setRequestProperty("Content-Length", data.length()+"");
                                conn.setDoOutput(true);
                                conn.getOutputStream().write(data.getBytes());
//		                    //连接
//		                    conn.connect();
//							//获取一个输出流
//							OutputStream out=conn.getOutputStream();
//							out.write(data.getBytes());
                                //获取服务器返回的状态吗
                                int code=conn.getResponseCode();
                                if(code==200){
                                    //获取服务器返回的输入流对象
                                    InputStream in= conn.getInputStream();
                                    System.out.println();
                                    result = StreamTools.readStreamToString(in);
                                    //更新UI
                                    runOnUiThread(new Runnable() {

                                        @Override
                                        public void run() {
                                            if(result.equals("success"))
                                                // TODO Auto-generated method stub
                                                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent();
                                            intent.setClassName("com.example.client_zhihu_hzy", "com.example.client_zhihu_hzy.MyFirstActivityDl");
                                            startActivity(intent);
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                        };
                    }.start();

                }
                break;
            case R.id.btnReg:
                //跳转到登录页面
                Intent intent=new Intent(this,MySecondActivityZc.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
            default:
                break;
        }

    }

}
