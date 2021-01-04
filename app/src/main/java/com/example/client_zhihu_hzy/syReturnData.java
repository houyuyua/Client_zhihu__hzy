package com.example.client_zhihu_hzy;
import java.util.List;
public class syReturnData {
    private Data data;
    private String message;
    private int code;

    public  class Data{
        public  List<questionData> questions;
        public String next_cursor;

        public  List<questionData> getList_data() { return questions; }
    }

    public Data getData(){ return data; }

    public String getMessage() { return message; }

    public int getCode() { return code; }

}
