package com.example.client_zhihu_hzy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
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


import android.os.Bundle;

public class MyFirstActivityDl extends FragmentActivity implements View.OnClickListener {

    public static final int TAB_HOME = 0;
    public static final int TAB_FIND = 1;
    public static final int TAB_MESS = 2;
    public static final int TAB_MINE = 3;

    private ViewPager viewPager;
    private RadioButton homeBtn;
    private RadioButton findBtn;
    private RadioButton messBtn;
    private RadioButton mineBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first_dl);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager)findViewById(R.id.fragment_viewpager);
        homeBtn = (RadioButton)findViewById(R.id.radio0);
        findBtn = (RadioButton)findViewById(R.id.radio1);
        messBtn = (RadioButton)findViewById(R.id.radio2);
        mineBtn = (RadioButton)findViewById(R.id.radio3);
        homeBtn.setOnClickListener(this);
        findBtn.setOnClickListener(this);
        messBtn.setOnClickListener(this);
        mineBtn.setOnClickListener(this);

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case TAB_HOME:
                        homeBtn.setChecked(true);
                        break;
                    case TAB_FIND:
                        findBtn.setChecked(true);
                        break;
                    case TAB_MESS:
                        messBtn.setChecked(true);
                        break;
                    case TAB_MINE:
                        mineBtn.setChecked(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.radio0:
                viewPager.setCurrentItem(TAB_HOME);
                break;
            case R.id.radio1:
                viewPager.setCurrentItem(TAB_FIND);
                break;
            case R.id.radio2:
                viewPager.setCurrentItem(TAB_MESS);
                break;
            case R.id.radio3:
                viewPager.setCurrentItem(TAB_MINE);
                break;

        }
    }

}