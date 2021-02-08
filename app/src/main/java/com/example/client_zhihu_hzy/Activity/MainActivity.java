package com.example.client_zhihu_hzy.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.client_zhihu_hzy.R;
import com.example.client_zhihu_hzy.ReturnData.LoginReturnData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin,btnReg;
    private EditText editName,editPwd;
    private String email;
    private String password;
    private SharedPreferences sp;
    private String result;
    private String originAddress = "http://47.116.128.111:8080/user/login";
    private LoginReturnData loginReturnData;
    private int httpcode;

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
    //实现onClick方法
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                sendRequestWithHttpURLConnection();
                break;
            case R.id.btnReg:
//                Intent intent_R = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent_R);
                Intent intent_regi = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent_regi,REQUEST_CODE);

                break;
            default:
                break;
        }
    }


    //发送请求
    private void sendRequestWithHttpURLConnection() {
        //输入的数据

        String email = editName.getText().toString().trim();
        String password = editPwd.getText().toString().trim();
//        String number = "1335259519@qq.com";
//        String password = "123456";

        if (email.isEmpty()) {
            Toast.makeText(MainActivity.this, "账号不能为空！", Toast.LENGTH_LONG).show();
            return;
        } else if (password.isEmpty()) {
            Toast.makeText(MainActivity.this, "密码不能为空！", Toast.LENGTH_LONG).show();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建jSON格式数据
                MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                JSONObject json = new JSONObject();
                try {
                    json.put("email",email);
                    json.put("password",password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //POST
                try {
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = RequestBody.create(JSON, String.valueOf(json));
                    System.out.println(json);
                    Request request = new Request.Builder()
                            .url(originAddress)
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    httpcode = response.code();
                    Log.d("MainActivity", "httpcode is " + response.code());
                    if (httpcode == 400 ){
                        Looper.prepare();
                        Toast.makeText(getApplicationContext(), "邮箱格式不正确或密码长度小于6位", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                    else if(httpcode == 200){
                        parseJSONWithJSONObject(responseData);
                    }
//                  showResponse(responseData);


                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
    }


    //解析回传JSON数据
    private void parseJSONWithJSONObject(String jsonData) {

        Gson gson = new Gson();
//        loginReturnData = gson.fromJson(jsonData, LoginReturnData.class);
        java.lang.reflect.Type type = new TypeToken< LoginReturnData>() {}.getType();
        System.out.println(jsonData);
        loginReturnData = gson.fromJson(jsonData, type);

        Log.d("MainActivity", "message is " + loginReturnData.getMessage());
        Log.d("MainActivity", "code is " + loginReturnData.getCode());
        Log.d("MainActivity", "token is " + loginReturnData.getData().getToken());
        //处理后续事件
        Handler(loginReturnData);

        SharedPreferences sp  = getSharedPreferences("loginToken",0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token","Bearer "+loginReturnData.getData().getToken());
        editor.putInt("id",loginReturnData.getData().getId());
        editor.commit();
        String token = sp.getString("token","");
        //
        Log.d("MainActivity","token is "+token);

    }


    private  void Handler(LoginReturnData loginReturnData){
        //登录成功
        if(loginReturnData.getMessage().equals("OK")){

            Intent intent_L =new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent_L);
            //这里是后续读入用户数据的逻辑
        }
        //登录失败
        else {
            loginError(loginReturnData);
        }
    }


    private void loginError(LoginReturnData loginReturnData){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(loginReturnData.getCode() == 2001) {
                    Toast.makeText(getApplicationContext(), "用户不存在，请重新输入！", Toast.LENGTH_SHORT).show();
                }
                else if(loginReturnData.getCode() == 2002){
                    Toast.makeText(getApplicationContext(), "密码错误，请重新输入！", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), loginReturnData.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}