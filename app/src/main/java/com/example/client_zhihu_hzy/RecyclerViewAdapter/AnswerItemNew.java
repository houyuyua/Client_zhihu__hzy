package com.example.client_zhihu_hzy.RecyclerViewAdapter;

public class AnswerItemNew {

    private int answererId;
    private String name;
    private int headImageId;
    private String answer ;
    private int agreeCount;
    private int commentCount;
    private int answerId;

    public AnswerItemNew(int answererId,String name, int headImageId, String answer, int agreeCount, int commentCount,int answerId){
        this.answererId=answererId;
        this.name=name;
        this.headImageId=headImageId;
        this.answer = answer;
        this.agreeCount = agreeCount;
        this.commentCount = commentCount;
        this.answerId = answerId;
    }

    public int getAnswererId() {
        return answererId;
    }

    public String getName() {
        return name;
    }

    public int getHeadImageId() {
        return headImageId;
    }

    public String getAnswer() {
        return answer;
    }

    public int getAgreeCount() {
        return agreeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getAnswerId() {
        return answerId;
    }

    @Override
    public String toString() {
        return "AnswerItemNew{" +
                "answererId=" + answererId +
                ", name='" + name + '\'' +
                ", headImageId=" + headImageId +
                ", answer='" + answer + '\'' +
                ", agreeCount=" + agreeCount +
                ", commentCount=" + commentCount +
                ", answerId=" + answerId +
                '}';
    }
}
