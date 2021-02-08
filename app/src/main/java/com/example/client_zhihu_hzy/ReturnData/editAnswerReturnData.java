package com.example.client_zhihu_hzy.ReturnData;

public class editAnswerReturnData {

    private SingleAnswerData data;
    private String message;
    private int status;


    public SingleAnswerData getData() { return data; }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "editAnswerReturnData{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
