package com.example.client_zhihu_hzy.ReturnData;

public class SingleAnswerDataView {
    private int id;
    private String content;



    private Creator creator;
    private int upvote_count;
    private int comment_count;


    public static class Creator {

        private int id;
        private String name;
        private String description;
        private String avatar_url;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        @Override
        public String toString() {
            return "Creator{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", avatar_url='" + avatar_url + '\'' +
                    '}';
        }
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }



    public Creator getCreator() {
        return creator;
    }

    public int getUpvote_count() {
        return upvote_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    @Override
    public String toString() {
        return "SingleAnswerDataView{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", creator=" + creator +
                ", upvote_count=" + upvote_count +
                ", comment_count=" + comment_count +
                '}';
    }
}
