package com.example.client_zhihu_hzy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity_fx extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_fx);
//    }
    public ListView l_v;
    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fx);
        l_v = (ListView) findViewById(R.id.listview);
        l_v.setAdapter(new MainActivity_fx.MyAdapter());

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
                view = View.inflate(MainActivity_fx.this, R.layout.new_item_layout, null);
//                LayoutInflater inflater = LayoutInflater.from(MainActivity_sy.this);
//                view = inflater.inflate(R.layout.new_item_layout,null);
            } else {
                view = convertView;
            }
            return view;
        }
    }

}