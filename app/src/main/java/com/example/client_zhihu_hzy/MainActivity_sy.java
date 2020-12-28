package com.example.client_zhihu_hzy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.client_zhihu_hzy.adapter.guanzhuFragment;
import com.example.client_zhihu_hzy.adapter.rebangFragment;
import com.example.client_zhihu_hzy.adapter.tuijianFragment;
import com.google.android.material.internal.ViewUtils;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity_sy extends AppCompatActivity {


    private Button iv_fabuqu;
    /**
     * 新方法
     */
    public ListView l_v;
    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sy);
//        l_v = (ListView) findViewById(R.id.listview);
//        l_v.setAdapter(new MyAdapter());
//        iv_fabuqu=(Button)findViewById(R.id.iv_fabuqu);
//        iv_fabuqu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity_sy.this,ques_send.class);
//                startActivity(intent);
//                Log.d("QQQQQQQQQQ","ssss");
//            }
//        });

    }



    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            view = null;
            if (convertView == null) {
                //上下文，条目布局的资源id，viewGroup
                view = View.inflate(MainActivity_sy.this, R.layout.new_item_layout, null);
//                LayoutInflater inflater = LayoutInflater.from(MainActivity_sy.this);
//                view = inflater.inflate(R.layout.new_item_layout,null);
            } else {
                view = convertView;
            }
            return view;
        }
    }

}