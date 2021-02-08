package com.example.client_zhihu_hzy.ReturnData;

import java.util.List;

public class HomeReturnData {
    private Data data;
    private String message;
    private int code;
    //缺少提问者的id
    public  class Data{
       // public  List<questionData> questions;
        private List<SingleQuestionData> questions;
        public String next_cursor;
        public List<SingleQuestionData> getList_data() { return questions; }
       // public  List<questionData> getList_data() { return questions;
        //         public  List<questionData> questions;
        //        public String next_cursor;
        //
        //        public  List<questionData> getList_data() { return questions; }
        //
        //}
    }

    public Data getData(){ return data; }

    public String getMessage() { return message; }

    public int getCode() { return code; }

    @Override
    public String toString() {
        return "HomeReturnData{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
    //    private List<SingleQuestionData> data;
//    private String message;
//    private int status;
//    private int total;
//
//
//
//    public List<SingleQuestionData> getData() {
//        return data;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public int getTotal() {
//        return total;
//    }
//
//    @Override
//    public String toString() {
//        return "HomeReturnData{" +
//                "data=" + data +
//                ", message='" + message + '\'' +
//                ", status=" + status +
//                ", total=" + total +
//                '}';
//    }
}
