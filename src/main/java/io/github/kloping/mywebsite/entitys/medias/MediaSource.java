package io.github.kloping.mywebsite.entitys.medias;

public class MediaSource {
    private String authorName;
    private String source;
    private String title;
    private String url;
    private Number likeCount;
    private String imgUrl;
    private String fromUrl;

    public MediaSource() {
    }

    @Override
    public String toString() {
        return String.format("来源:%s\r\n==========\r\n作者:%s\r\n==========\n标题:%s\r\n==========\n点赞量:%s\r\n==========\n播放地址:%s\r\n", source, authorName, title, likeCount, url);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public MediaSource setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public MediaSource setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getSource() {
        return source;
    }

    public MediaSource setSource(String source) {
        this.source = source;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public MediaSource setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public MediaSource setUrl(String url) {
        this.url = url;
        return this;
    }

    public Number getLikeCount() {
        return likeCount;
    }

    public MediaSource setLikeCount(Number likeCount) {
        this.likeCount = likeCount;
        return this;
    }
}
