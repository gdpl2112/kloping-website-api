package io.github.kloping.mywebsite.domain.bo.duiTang;

import lombok.Getter;

/**
 * @author github-kloping
 */
@Getter
public class DuiTangResponse {
    private Data data;
    private Number status;

    public DuiTangResponse setData(Data data) {
        this.data = data;
        return this;
    }

    public DuiTangResponse setStatus(Number status) {
        this.status = status;
        return this;
    }
}