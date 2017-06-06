package com.vary.salaryandcash.mvp.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-06-05.
 */

public class Salary implements Serializable {

    private String detailDescription;
    private String previewDescription;
    private int id;
    private String title;
    private String microVideo;
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
