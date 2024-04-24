package io.github.kloping.mywebsite.domain.bo.position;

public class PositionInfo {
    private Detail detail;
    private Info info;

    public Detail getDetail() {
        return this.detail;
    }

    public PositionInfo setDetail(Detail detail) {
        this.detail = detail;
        return this;
    }

    public Info getInfo() {
        return this.info;
    }

    public PositionInfo setInfo(Info info) {
        this.info = info;
        return this;
    }

    public String getAllName() {
        return detail.getProvince() + detail.getCity() + detail.getDistrict() + (detail.getDistrict().equals(detail.getName()) ? "" : detail.getName());
    }
}