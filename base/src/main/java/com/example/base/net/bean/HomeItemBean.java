package com.example.base.net.bean;

public class HomeItemBean {
    private int drawableId;
    private String url;

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HomeItemBean(int drawableId, String url){

        this.drawableId = drawableId;
        this.url = url;
    }
}
