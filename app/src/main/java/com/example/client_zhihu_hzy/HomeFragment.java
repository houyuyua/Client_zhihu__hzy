package com.example.client_zhihu_hzy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.os.Bundle;
public class HomeFragment extends Fragment {
    private Button iv_fabuqu;
    /**
     * 新方法
     */
    public ListView l_v;
    public View view;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_main_sy,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
        l_v = (ListView) getActivity().findViewById(R.id.listview);
        l_v.setAdapter(new MyAdapter());
        iv_fabuqu=(Button)getActivity().findViewById(R.id.iv_fabuqu);
        iv_fabuqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ques_send.class);
                startActivity(intent);
                Log.d("QQQQQQQQQQ","ssss");
            }
        });

    }
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
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
                view = View.inflate(getActivity(), R.layout.new_item_layout, null);
//                LayoutInflater inflater = LayoutInflater.from(MainActivity_sy.this);
//                view = inflater.inflate(R.layout.new_item_layout,null);
            } else {
                view = convertView;
            }
            return view;
        }
    }

}


