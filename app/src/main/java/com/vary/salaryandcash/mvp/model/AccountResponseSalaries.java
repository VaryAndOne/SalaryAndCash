package com.vary.salaryandcash.mvp.model;

public class AccountResponseSalaries {
    private String detailDescription;
    private String image;
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

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
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
