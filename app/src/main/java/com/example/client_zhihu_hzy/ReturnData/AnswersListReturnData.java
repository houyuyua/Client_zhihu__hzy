package com.example.client_zhihu_hzy.ReturnData;

import java.util.List;

public class AnswersListReturnData {
    private Data data;
    private String message;
    private int code;

    //缺少提问者的id
    public class Data {
        private List<SingleAnswerData> answers;
        public String next_cursor;
        public String getNext_cursor() { return next_cursor; }
        public List<SingleAnswerData> getList_data() { return answers; }

        @Override
        public String toString() {
            return "Data{" +
                    "answers=" + answers +
                    ", next_cursor='" + next_cursor + '\'' +
                    '}';
        }
    }

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "AnswersListReturnData{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }


}

   // private List<SingleAnswerData> data;
//    private String message;
//    private int status;
//    private int total;
//
//    public List<SingleAnswerData> getData() {
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
//        return "AnswersListReturnData{" +
//                "data=" + data +
//                ", message='" + message + '\'' +
//                ", status=" + status +
//                ", total=" + total +
//                '}';
//    }