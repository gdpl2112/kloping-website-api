package io.github.kloping.mywebsite.entitys.baiduShitu.response;

public class DataList {
    private String fromUrl;
    private Number width;
    private String objUrl;
    private Number index;
    private String contsign;
    private String thumbUrl;
    private Number height;

    public String getFromUrl() {
        return this.fromUrl;
    }

    public DataList setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
        return this;
    }

    public Number getWidth() {
        return this.width;
    }

    public DataList setWidth(Number width) {
        this.width = width;
        return this;
    }

    public String getObjUrl() {
        return this.objUrl;
    }

    public DataList setObjUrl(String objUrl) {
        this.objUrl = objUrl;
        return this;
    }

    public Number getIndex() {
        return this.index;
    }

    public DataList setIndex(Number index) {
        this.index = index;
        return this;
    }

    public String getContsign() {
        return this.contsign;
    }

    public DataList setContsign(String contsign) {
        this.contsign = contsign;
        return this;
    }

    public String getThumbUrl() {
        return this.thumbUrl;
    }

    public DataList setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
        return this;
    }

    public Number getHeight() {
        return this.height;
    }

    public DataList setHeight(Number height) {
        this.height = height;
        return this;
    }
}