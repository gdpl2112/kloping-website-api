package io.github.kloping.mywebsite.entitys.ks;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author HRS-Computer
 */
public class RequestData {
    @JSONField(ordinal = 0)
    private String fid;
    @JSONField(ordinal = 1)
    private String shareToken;
    @JSONField(ordinal = 2)
    private String shareObjectId;
    @JSONField(ordinal = 3)
    private String shareMethod;
    @JSONField(ordinal = 4)
    private String shareResourceType;
    @JSONField(ordinal = 5)
    private String shareChannel = "share_copylink";
    @JSONField(ordinal = 6)
    private String kpn;
    @JSONField(ordinal = 7)
    private String subBiz;
    @JSONField(ordinal = 8)
    private String env = "SHARE_VIEWER_ENV_TX_TRICK";
    @JSONField(ordinal = 9)
    private String h5Domain = "kph8gvfz.m.chenzhongtech.com";
    @JSONField(ordinal = 10)
    private String photoId;
    @JSONField(ordinal = 11)
    private Boolean isLongVideo = false;

    public String getFid() {
        return this.fid;
    }

    public RequestData setFid(String fid) {
        this.fid = fid;
        return this;
    }

    public String getShareMethod() {
        return this.shareMethod;
    }

    public RequestData setShareMethod(String shareMethod) {
        this.shareMethod = shareMethod;
        return this;
    }

    public String getKpn() {
        return this.kpn;
    }

    public RequestData setKpn(String kpn) {
        this.kpn = kpn;
        return this;
    }

    public String getSubBiz() {
        return this.subBiz;
    }

    public RequestData setSubBiz(String subBiz) {
        this.subBiz = subBiz;
        return this;
    }

    public String getPhotoId() {
        return this.photoId;
    }

    public RequestData setPhotoId(String photoId) {
        this.photoId = photoId;
        return this;
    }

    public Boolean getIsLongVideo() {
        return this.isLongVideo;
    }

    public RequestData setIsLongVideo(Boolean isLongVideo) {
        this.isLongVideo = isLongVideo;
        return this;
    }

    public String getShareToken() {
        return this.shareToken;
    }

    public RequestData setShareToken(String shareToken) {
        this.shareToken = shareToken;
        return this;
    }

    public String getShareResourceType() {
        return this.shareResourceType;
    }

    public RequestData setShareResourceType(String shareResourceType) {
        this.shareResourceType = shareResourceType;
        return this;
    }

    public String getEnv() {
        return this.env;
    }

    public RequestData setEnv(String env) {
        this.env = env;
        return this;
    }

    public String getH5Domain() {
        return this.h5Domain;
    }

    public RequestData setH5Domain(String h5Domain) {
        this.h5Domain = h5Domain;
        return this;
    }

    public String getShareChannel() {
        return this.shareChannel;
    }

    public RequestData setShareChannel(String shareChannel) {
        this.shareChannel = shareChannel;
        return this;
    }

    public String getShareObjectId() {
        return this.shareObjectId;
    }

    public RequestData setShareObjectId(String shareObjectId) {
        this.shareObjectId = shareObjectId;
        return this;
    }
}