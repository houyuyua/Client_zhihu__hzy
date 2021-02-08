package com.example.client_zhihu_hzy.ReturnData;

public class EditQuestionReturnData {


    private SingleQuestionData data;
    private String message;
    private int status;


    public SingleQuestionData getData() { return data; }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "EditQuestionReturnData{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
//    private String message;
//    private int status;
//
//    public int getStatus() {
//        return status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    @Override
//    public String toString() {
//        return "EditQuestionReturnData{" +
//                "message='" + message + '\'' +
//                ", status=" + status +
//                '}';
//    }
