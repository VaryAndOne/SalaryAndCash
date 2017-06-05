package com.vary.salaryandcash.mvp.model;

/**
 * Created by Administrator on 2017-06-05.
 */

public class Salary {

    private String detailDescription;
    private String previewDescription;
    private int id;
    private String title;
    private String microVideo;

    public String getDetailDescription() {
        return this.detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getPreviewDescription() {
        return this.previewDescription;
    }

    public void setPreviewDescription(String previewDescription) {
        this.previewDescription = previewDescription;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMicroVideo() {
        return this.microVideo;
    }

    public void setMicroVideo(String microVideo) {
        this.microVideo = microVideo;
    }
}
