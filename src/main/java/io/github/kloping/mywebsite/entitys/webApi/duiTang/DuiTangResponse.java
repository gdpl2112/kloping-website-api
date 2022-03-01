package io.github.kloping.mywebsite.entitys.webApi.duiTang;

/**
 * @author github-kloping
 */
public class DuiTangResponse {
    private Data data;
    private Number status;

    public Data getData() {
        return this.data;
    }

    public DuiTangResponse setData(Data data) {
        this.data = data;
        return this;
    }

    public Number getStatus() {
        return this.status;
    }

    public DuiTangResponse setStatus(Number status) {
        this.status = status;
        return this;
    }
}