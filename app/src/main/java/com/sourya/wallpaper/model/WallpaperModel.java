package com.sourya.wallpaper.model;

public class WallpaperModel {

    private String image, id;

    public WallpaperModel() {
    }

    public WallpaperModel(String image, String id) {
        this.image = image;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
