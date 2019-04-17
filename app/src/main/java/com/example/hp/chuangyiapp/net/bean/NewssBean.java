package com.example.hp.chuangyiapp.net.bean;

import java.util.List;

public class NewssBean {


    private List<NewslistBean> newslist;

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * news_id : 0
         * title : string
         * content : string
         * photo : string
         * commentsnum : 0
         * time : string
         */

        private int news_id;
        private String title;
        private String content;
        private String photo;
        private int commentsnum;
        private String time;

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

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

        public int getCommentsnum() {
            return commentsnum;
        }

        public void setCommentsnum(int commentsnum) {
            this.commentsnum = commentsnum;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
