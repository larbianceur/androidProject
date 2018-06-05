package com.ezi.larbianceur.esigym.model;



/**
 * Created by Larbi Anceur on 25-May-18.
 */


public class Item {
    private String detaill;
    private String videoUrl;

    public Item(){

    }

    public Item(String detaill, String videoUrl) {
        this.detaill = detaill;
        this.videoUrl = videoUrl;
    }

    public String getDetaill() {
        return detaill;
    }

    public void setDetaill(String detaill) {
        this.detaill = detaill;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
