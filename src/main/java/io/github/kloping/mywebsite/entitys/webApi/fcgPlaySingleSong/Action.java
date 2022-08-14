package io.github.kloping.mywebsite.entitys.webApi.fcgPlaySingleSong;

import com.alibaba.fastjson.annotation.JSONField;

public class Action {
    private Number msgpay;
    private Number alert;
    private Number msgid;
    private Number msgshare;
    private Number icons;
    private Number msgdown;
    private Number msgfav;
    @JSONField(name = "switch")
    private Number switch_;

    public Number getMsgpay() {
        return this.msgpay;
    }

    public Action setMsgpay(Number msgpay) {
        this.msgpay = msgpay;
        return this;
    }

    public Number getAlert() {
        return this.alert;
    }

    public Action setAlert(Number alert) {
        this.alert = alert;
        return this;
    }

    public Number getMsgid() {
        return this.msgid;
    }

    public Action setMsgid(Number msgid) {
        this.msgid = msgid;
        return this;
    }

    public Number getMsgshare() {
        return this.msgshare;
    }

    public Action setMsgshare(Number msgshare) {
        this.msgshare = msgshare;
        return this;
    }

    public Number getIcons() {
        return this.icons;
    }

    public Action setIcons(Number icons) {
        this.icons = icons;
        return this;
    }

    public Number getMsgdown() {
        return this.msgdown;
    }

    public Action setMsgdown(Number msgdown) {
        this.msgdown = msgdown;
        return this;
    }

    public Number getMsgfav() {
        return this.msgfav;
    }

    public Action setMsgfav(Number msgfav) {
        this.msgfav = msgfav;
        return this;
    }

    public Number getSwitch() {
        return this.switch_;
    }

    public Action setSwitch(Number switch_) {
        this.switch_ = switch_;
        return this;
    }
}