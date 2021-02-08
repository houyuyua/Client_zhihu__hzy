
package com.example.client_zhihu_hzy.ReturnData;

public class SingleQuestionData {


    private int id;
    private String title;
    private String content;
    private int answer_count;
    private int view_count;
    private int update_at;
    private Creator creator;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public int getView_count() {
        return view_count;
    }

    public int getUpdate_at() {
        return update_at;
    }

    public static class Creator{
        private int id;
        private String name;
        private int gender;
        private String description;
        private String avatar_url;


        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getGender() {
            return gender;
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
                    ", gender=" + gender +
                    ", description='" + description + '\'' +
                    ", avatar_url='" + avatar_url + '\'' +
                    '}';
        }
    }

    public Creator getCreator() {
        return creator;
    }

    @Override
    public String toString() {
        return "SingleQuestionData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", answer_count=" + answer_count +
                ", view_count=" + view_count +
                ", update_at=" + update_at +
                ", creator=" + creator +
                '}';
    }


//    private int id;
//    private String title;
//    private String desc;
//    private Questioner questioner;
//    private String createdAt;
//    private String updatedAt;
//    private int answersCount;
//    private int viewCount;
//
//    public static class Questioner {
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
//            return "Questioner{" +
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
//    public String getTitle() {
//        return title;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public Questioner getQuestioner() {
//        return questioner;
//    }
//
//    public int getAnswersCount() {
//        return answersCount;
//    }
//
//    public int getViewCount() {
//        return viewCount;
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
//        return "SingleQuestionData{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", desc='" + desc + '\'' +
//                ", questioner=" + questioner +
//                ", createdAt='" + createdAt + '\'' +
//                ", updatedAt='" + updatedAt + '\'' +
//                ", answersCount=" + answersCount +
//                ", viewCount=" + viewCount +
//                '}';
//    }
}
