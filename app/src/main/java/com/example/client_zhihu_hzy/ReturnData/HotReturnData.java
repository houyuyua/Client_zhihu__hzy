package com.example.client_zhihu_hzy.ReturnData;

import java.util.List;

public class HotReturnData {
    private Data data;
    private String message;
    private int code;
    //缺少提问者的id
    public  class Data{
        // public  List<questionData> questions;
        private List<SingleHotQuestionData> questions;
        public String next_cursor;
        public List<SingleHotQuestionData> getList_data() { return questions; }
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


}
