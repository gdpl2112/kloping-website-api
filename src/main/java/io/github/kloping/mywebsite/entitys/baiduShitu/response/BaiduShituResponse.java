package io.github.kloping.mywebsite.entitys.baiduShitu.response;

public class BaiduShituResponse {
    private Data data;
    private Number status;

    public Data getData() {
        return this.data;
    }

    public BaiduShituResponse setData(Data data) {
        this.data = data;
        return this;
    }

    public Number getStatus() {
        return this.status;
    }

    public BaiduShituResponse setStatus(Number status) {
        this.status = status;
        return this;
    }
}