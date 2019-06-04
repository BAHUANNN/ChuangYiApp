package com.example.base.net.bean;

import java.util.List;

public class StatesBean {


    private List<FeedlistBean> feedlist;

    public List<FeedlistBean> getFeedlist() {
        return feedlist;
    }

    public void setFeedlist(List<FeedlistBean> feedlist) {
        this.feedlist = feedlist;
    }

    public static class FeedlistBean {
        /**
         * content : string
         * feed_id : 0
         * username : string
         * time : string
         */

        private String content;
        private int feed_id;
        private String username;
        private String time;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getFeed_id() {
            return feed_id;
        }

        public void setFeed_id(int feed_id) {
            this.feed_id = feed_id;
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
