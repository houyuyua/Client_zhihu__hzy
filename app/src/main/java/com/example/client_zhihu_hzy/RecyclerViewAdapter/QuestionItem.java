package com.example.client_zhihu_hzy.RecyclerViewAdapter;

import android.graphics.Bitmap;

public class QuestionItem {


    private int ViewsCount;
    private int AnswersCount;
    private int uId;
    private int questionId;
    private String title;
    private String describe;
    private String name;
    private Bitmap headImageId;
//    private String avatar_url;
//    private String agreeNumber;
//    private String commentNumber;


    public QuestionItem(int uId, int questionId, String title, Bitmap headImageId, String name, String describe, int ViewsCount, int AnswersCount){
        this.uId=uId;
        this.title=title;
        this.describe=describe;
        this.name = name;
//        this.headImageId =headImageId;
        this.headImageId = headImageId;
        this.ViewsCount = ViewsCount;
        this.AnswersCount = AnswersCount;
        this.questionId = questionId;
    }

    public int getuId() {
        return uId;
    }

    public String getTitle(){
        return title;
    }

    public String getDescribe() {
        return describe;
    }

    public String getName() {
        return name;
    }

//    public String getAvatar_url() {
//        return avatar_url;
//    }
    public Bitmap getHeadImageId() {
        return headImageId;
    }

    public int getViewsCount() {
        return ViewsCount;
    }

    public int getAnswersCount() {
        return AnswersCount;
    }

    public int getQuestionId() {
        return questionId;
    }

    @Override
    public String toString() {
        return "QuestionItem{" +
                "ViewsCount=" + ViewsCount +
                ", AnswersCount=" + AnswersCount +
                ", uId=" + uId +
                ", questionId=" + questionId +
                ", title='" + title + '\'' +
                ", describe='" + describe + '\'' +
                ", name='" + name + '\'' +
                ", headImageId=" + headImageId +
                '}';
    }
}
