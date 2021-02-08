package com.example.client_zhihu_hzy.ReturnData;

public class SingleAnswerData {
    private int id;
    private String content;
    private int view_count;


    private Creator creator;
    private int upvote_count;
    private int comment_count;
    private int create_at;
    private int update_at;

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

    public int getView_count() {
        return view_count;
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

    public int getCreate_at() {
        return create_at;
    }

    public int getUpdate_at() {
        return update_at;
    }

    @Override
    public String toString() {
        return "SingleAnswerData{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", view_count=" + view_count +
                ", creator=" + creator +
                ", upvote_count=" + upvote_count +
                ", comment_count=" + comment_count +
                ", create_at=" + create_at +
                ", update_at=" + update_at +
                '}';
    }
}
//   private int id;
//    private String content;
//    private String questionTitle;
//    private Answerer answerer;
//    private int supportersCount;
//    private int voted;
//    private String createdAt;
//    private String updatedAt;
//
//    public static class Answerer {
//        private int id;
//        private String name;
//        private String desc;
//
//        public int getId() {
//            return id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public String getDesc() {
//            return desc;
//        }
//
//        @Override
//        public String toString() {
//            return "Answerer{" +
//                    "id=" + id +
//                    ", name='" + name + '\'' +
//                    ", desc='" + desc + '\'' +
//                    '}';
//        }
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public String getQuestionTitle() {
//        return questionTitle;
//    }
//
//    public Answerer getAnswerer() {
//        return answerer;
//    }
//
//    public int getSupportersCount() {
//        return supportersCount;
//    }
//
//    public int getVoted() {
//        return voted;
//    }
//
//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    public String getUpdatedAt() {
//        return updatedAt;
//    }
//
//
//    @Override
//    public String toString() {
//        return "SingleAnswerData{" +
//                "id=" + id +
//                ", content='" + content + '\'' +
//                ", questionTitle='" + questionTitle + '\'' +
//                ", answerer=" + answerer +
//                ", supportersCount=" + supportersCount +
//                ", voted=" + voted +
//                ", createdAt='" + createdAt + '\'' +
//                ", updatedAt='" + updatedAt + '\'' +
//                '}';
//    }