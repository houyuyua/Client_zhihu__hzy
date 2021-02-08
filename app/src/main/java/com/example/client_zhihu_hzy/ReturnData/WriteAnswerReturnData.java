package com.example.client_zhihu_hzy.ReturnData;

public class WriteAnswerReturnData {
    private String message;
    private int code;
    private SingleAnswerData data;

    public String getMessage() {
        return message;
    }

    public int getCode() { return code; }

    public SingleAnswerData getData() {
        return data;
    }


    @Override
    public String toString() {
        return "WriteAnswerReturnData{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
