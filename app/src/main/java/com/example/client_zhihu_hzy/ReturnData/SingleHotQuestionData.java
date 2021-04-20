package com.example.client_zhihu_hzy.ReturnData;

public class SingleHotQuestionData {
    private int id;
    private String title;
    private String content;
    private int answer_count;
    private int view_count;
    private int update_at;
    private int heat;
    private Answer answer;

    public static class Answer {
        private int id;
        private String content;
        private int upvote_count;
        private int comment_count;


        private Creator creator;
        public static class Creator{
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
        }

        public int getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public int getUpvote_count() {
            return upvote_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public Creator getCreator() {
            return creator;
        }
    }

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

    public int getHeat() {
        return heat;
    }

    public Answer getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "SingleHotQuestionData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", answer_count=" + answer_count +
                ", view_count=" + view_count +
                ", update_at=" + update_at +
                ", heat=" + heat +
                ", answer=" + answer +
                '}';
    }
}
