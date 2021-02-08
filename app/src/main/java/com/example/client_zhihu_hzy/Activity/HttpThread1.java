package com.example.client_zhihu_hzy.Activity;



import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpThread1 extends Thread{
    private String url;
    private String editName;
    public HttpThread1(String url, String editName) {
        this.url = url;
        this.editName = editName;
    }
    private void doGet()//使用get方式给服务器传参数
    {
        try {
            url = url + "?email=" + editName ;//通过get方式传参数
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            Log.d("HttpThread1", sb.toString());//将从服务器接收来的数据打印出来
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        doGet();
    }


}

