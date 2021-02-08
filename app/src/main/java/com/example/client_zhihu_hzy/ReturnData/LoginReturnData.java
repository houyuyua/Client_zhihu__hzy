package com.example.client_zhihu_hzy.ReturnData;

public class LoginReturnData {
    private String message;
    private int code;
    private Data data;

    public  static class Data{
        private int id;
        private String token;

        public int getId() {
            return id;
        }

        public String getToken() {
            return token;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setToken(String token) {
            this.token = token;
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

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LoginReturnData{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
