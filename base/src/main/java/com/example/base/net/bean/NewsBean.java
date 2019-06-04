package com.example.base.net.bean;

import java.util.List;

public class NewsBean {


    /**
     * title : string
     * content : string
     * photo : string
     * time : string
     * comments_list : [{"comment_id":0,"content":"string","username":"string","time":"string"}]
     */

    private String title;
    private String content;
    private String photo;
    private String time;
    private List<CommentsListBean> comments_list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<CommentsListBean> getComments_list() {
        return comments_list;
    }

    public void setComments_list(List<CommentsListBean> comments_list) {
        this.comments_list = comments_list;
    }

    public static class CommentsListBean {
        /**
         * comment_id : 0
         * content : string
         * username : string
         * time : string
         */

        private int comment_id;
        private String content;
        private String username;
        private String time;

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
