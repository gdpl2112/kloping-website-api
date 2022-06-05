package io.github.kloping.mywebsite.entitys.webApi.ks.data0;

import com.alibaba.fastjson.annotation.JSONField;

public class DataRespon {
    private Number result;
    private Relate relate;
    private String srsid;
    private Feeds[] feeds;
    private String pcursor;
    private String llsid;
    private String host_name;

    public Number getResult() {
        return this.result;
    }

    public DataRespon setResult(Number result) {
        this.result = result;
        return this;
    }

    public Relate getRelate() {
        return this.relate;
    }

    public DataRespon setRelate(Relate relate) {
        this.relate = relate;
        return this;
    }

    public String getSrsid() {
        return this.srsid;
    }

    public DataRespon setSrsid(String srsid) {
        this.srsid = srsid;
        return this;
    }

    public Feeds[] getFeeds() {
        return this.feeds;
    }

    public DataRespon setFeeds(Feeds[] feeds) {
        this.feeds = feeds;
        return this;
    }

    public String getPcursor() {
        return this.pcursor;
    }

    public DataRespon setPcursor(String pcursor) {
        this.pcursor = pcursor;
        return this;
    }

    public String getLlsid() {
        return this.llsid;
    }

    public DataRespon setLlsid(String llsid) {
        this.llsid = llsid;
        return this;
    }

    @JSONField(name = "host-name")
    public String getHost_name() {
        return this.host_name;
    }

    @JSONField(name = "host-name")
    public DataRespon setHost_name(String host_name) {
        this.host_name = host_name;
        return this;
    }
}