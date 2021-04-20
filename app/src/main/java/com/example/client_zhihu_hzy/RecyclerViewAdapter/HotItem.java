package com.example.client_zhihu_hzy.RecyclerViewAdapter;

public class HotItem {

    private int heat;
    private int uId;
    private int questionId;
    private String title;
    private int headImageId;
    private int numQ;

//    private String avatar_url;
//    private String agreeNumber;
//    private String commentNumber;


    public HotItem(int heat,int uId, int questionId, String title, int headImageId,int numQ){
        this.heat=heat;
        this.uId=uId;
        this.title=title;

//        this.headImageId =headImageId;
        this.headImageId = headImageId;

        this.questionId = questionId;
        this.numQ = numQ;
    }

    public int getNumQ() {
        return numQ;
    }

    public int getHeat() {
        return heat;
    }

    public int getuId() {
        return uId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public int getHeadImageId() {
        return headImageId;
    }

    @Override
    public String toString() {
        return "HotItem{" +
                "heat=" + heat +
                ", uId=" + uId +
                ", questionId=" + questionId +
                ", title='" + title + '\'' +
                ", headImageId=" + headImageId +
                ", numQ=" + numQ +
                '}';
    }
}
