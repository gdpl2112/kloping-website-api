package io.github.kloping.mywebsite.entitys.webApi.ks;

import com.alibaba.fastjson.annotation.JSONField;

public class DataResponse {
    private Number result;
    private ShareInfo shareInfo;
    private Comments[] comments;
    private Atlas atlas;
    private Counts counts;
    private Photo photo;
    private String host_name;
    private String mp4Url;
    private Photos[] photos;

    public Number getResult() {
        return this.result;
    }

    public DataResponse setResult(Number result) {
        this.result = result;
        return this;
    }

    public ShareInfo getShareInfo() {
        return this.shareInfo;
    }

    public DataResponse setShareInfo(ShareInfo shareInfo) {
        this.shareInfo = shareInfo;
        return this;
    }

    public Comments[] getComments() {
        return this.comments;
    }

    public DataResponse setComments(Comments[] comments) {
        this.comments = comments;
        return this;
    }

    public Atlas getAtlas() {
        return this.atlas;
    }

    public DataResponse setAtlas(Atlas atlas) {
        this.atlas = atlas;
        return this;
    }

    public Counts getCounts() {
        return this.counts;
    }

    public DataResponse setCounts(Counts counts) {
        this.counts = counts;
        return this;
    }

    public Photo getPhoto() {
        return this.photo;
    }

    public DataResponse setPhoto(Photo photo) {
        this.photo = photo;
        return this;
    }

    @JSONField(name = "host-name")
    public String gethost_name() {
        return this.host_name;
    }

    @JSONField(name = "host-name")
    public DataResponse sethost_name(String host_name) {
        this.host_name = host_name;
        return this;
    }

    public String getMp4Url() {
        return this.mp4Url;
    }

    public DataResponse setMp4Url(String mp4Url) {
        this.mp4Url = mp4Url;
        return this;
    }

    public Photos[] getPhotos() {
        return this.photos;
    }

    public DataResponse setPhotos(Photos[] photos) {
        this.photos = photos;
        return this;
    }
}