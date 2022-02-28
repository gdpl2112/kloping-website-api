package io.github.kloping.mywebsite.plugins.entity.kugouDetail;

import com.alibaba.fastjson.annotation.JSONField;

public class Info {
    private Number srctype;
    private Number bitrate;
    private String source;
    private String rp_type;
    private String songname_original;
    private Number audio_id;
    private String othername;
    private Number price;
    private String mvhash;
    private Number feetype;
    private Number pay_type_sq;
    private String extname;
    private Number rp_publish;
    private Number fold_type;
    private String othername_original;
    private String songname;
    private Number pkg_price_320;
    private Number sqprivilege;
    private Number sqfilesize;
    private String filename;
    private Number m4afilesize;
    private String topic;
    private String album_id;
    private Number pkg_price;
    private Number pkg_price_sq;
    private String hash;
    private String singername;
    private Number fail_process_320;
    private Number fail_process_sq;
    private Number fail_process;
    private String sqhash;
    private Number isnew;
    private Number privilege;
    private Number filesize;
    private Number price_sq;
    private Number duration;
    private Number ownercount;
    private Trans_param trans_param;
    private Number pay_type_320;
    private String album_name;
    private Number old_cpy;
    private Number album_audio_id;
    private Number pay_type;
    @JSONField(name = "320hash")
    private String hash320;
    @JSONField(name = "320filesize")
    private Number filesize320;
    private Number sourceid;
    private Number Accompany;
    @JSONField(name = "320privilege")
    private Number privilege320;
    private Number price_320;
    private String topic_url;
    private Number isoriginal;

    public String getHash320() {
        return hash320;
    }

    public void setHash320(String hash320) {
        this.hash320 = hash320;
    }

    public Number getFilesize320() {
        return filesize320;
    }

    public void setFilesize320(Number filesize320) {
        this.filesize320 = filesize320;
    }

    public Number getPrivilege320() {
        return privilege320;
    }

    public void setPrivilege320(Number privilege320) {
        this.privilege320 = privilege320;
    }

    public Number getSrctype() {
        return this.srctype;
    }

    public Info setSrctype(Number srctype) {
        this.srctype = srctype;
        return this;
    }

    public Number getBitrate() {
        return this.bitrate;
    }

    public Info setBitrate(Number bitrate) {
        this.bitrate = bitrate;
        return this;
    }

    public String getSource() {
        return this.source;
    }

    public Info setSource(String source) {
        this.source = source;
        return this;
    }

    public String getRp_type() {
        return this.rp_type;
    }

    public Info setRp_type(String rp_type) {
        this.rp_type = rp_type;
        return this;
    }

    public Number getAudio_id() {
        return this.audio_id;
    }

    public Info setAudio_id(Number audio_id) {
        this.audio_id = audio_id;
        return this;
    }

    public String getSongname_original() {
        return this.songname_original;
    }

    public Info setSongname_original(String songname_original) {
        this.songname_original = songname_original;
        return this;
    }

    public String getOthername() {
        return this.othername;
    }

    public Info setOthername(String othername) {
        this.othername = othername;
        return this;
    }

    public Number getPrice() {
        return this.price;
    }

    public Info setPrice(Number price) {
        this.price = price;
        return this;
    }

    public String getMvhash() {
        return this.mvhash;
    }

    public Info setMvhash(String mvhash) {
        this.mvhash = mvhash;
        return this;
    }

    public Number getFeetype() {
        return this.feetype;
    }

    public Info setFeetype(Number feetype) {
        this.feetype = feetype;
        return this;
    }

    public String getExtname() {
        return this.extname;
    }

    public Info setExtname(String extname) {
        this.extname = extname;
        return this;
    }

    public Number getPay_type_sq() {
        return this.pay_type_sq;
    }

    public Info setPay_type_sq(Number pay_type_sq) {
        this.pay_type_sq = pay_type_sq;
        return this;
    }

    public Number getRp_publish() {
        return this.rp_publish;
    }

    public Info setRp_publish(Number rp_publish) {
        this.rp_publish = rp_publish;
        return this;
    }

    public Number getFold_type() {
        return this.fold_type;
    }

    public Info setFold_type(Number fold_type) {
        this.fold_type = fold_type;
        return this;
    }

    public String getOthername_original() {
        return this.othername_original;
    }

    public Info setOthername_original(String othername_original) {
        this.othername_original = othername_original;
        return this;
    }

    public String getSongname() {
        return this.songname;
    }

    public Info setSongname(String songname) {
        this.songname = songname;
        return this;
    }

    public Number getPkg_price_320() {
        return this.pkg_price_320;
    }

    public Info setPkg_price_320(Number pkg_price_320) {
        this.pkg_price_320 = pkg_price_320;
        return this;
    }

    public Number getSqprivilege() {
        return this.sqprivilege;
    }

    public Info setSqprivilege(Number sqprivilege) {
        this.sqprivilege = sqprivilege;
        return this;
    }

    public Number getSqfilesize() {
        return this.sqfilesize;
    }

    public Info setSqfilesize(Number sqfilesize) {
        this.sqfilesize = sqfilesize;
        return this;
    }

    public String getFilename() {
        return this.filename;
    }

    public Info setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public Number getM4afilesize() {
        return this.m4afilesize;
    }

    public Info setM4afilesize(Number m4afilesize) {
        this.m4afilesize = m4afilesize;
        return this;
    }

    public String getTopic() {
        return this.topic;
    }

    public Info setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getAlbum_id() {
        return this.album_id;
    }

    public Info setAlbum_id(String album_id) {
        this.album_id = album_id;
        return this;
    }

    public Number getPkg_price() {
        return this.pkg_price;
    }

    public Info setPkg_price(Number pkg_price) {
        this.pkg_price = pkg_price;
        return this;
    }

    public Number getPkg_price_sq() {
        return this.pkg_price_sq;
    }

    public Info setPkg_price_sq(Number pkg_price_sq) {
        this.pkg_price_sq = pkg_price_sq;
        return this;
    }

    public String getHash() {
        return this.hash;
    }

    public Info setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getSingername() {
        return this.singername;
    }

    public Info setSingername(String singername) {
        this.singername = singername;
        return this;
    }

    public Number getFail_process_320() {
        return this.fail_process_320;
    }

    public Info setFail_process_320(Number fail_process_320) {
        this.fail_process_320 = fail_process_320;
        return this;
    }

    public Number getFail_process_sq() {
        return this.fail_process_sq;
    }

    public Info setFail_process_sq(Number fail_process_sq) {
        this.fail_process_sq = fail_process_sq;
        return this;
    }

    public Number getFail_process() {
        return this.fail_process;
    }

    public Info setFail_process(Number fail_process) {
        this.fail_process = fail_process;
        return this;
    }

    public String getSqhash() {
        return this.sqhash;
    }

    public Info setSqhash(String sqhash) {
        this.sqhash = sqhash;
        return this;
    }

    public Number getIsnew() {
        return this.isnew;
    }

    public Info setIsnew(Number isnew) {
        this.isnew = isnew;
        return this;
    }

    public Number getPrivilege() {
        return this.privilege;
    }

    public Info setPrivilege(Number privilege) {
        this.privilege = privilege;
        return this;
    }

    public Number getFilesize() {
        return this.filesize;
    }

    public Info setFilesize(Number filesize) {
        this.filesize = filesize;
        return this;
    }

    public Number getPrice_sq() {
        return this.price_sq;
    }

    public Info setPrice_sq(Number price_sq) {
        this.price_sq = price_sq;
        return this;
    }

    public Number getDuration() {
        return this.duration;
    }

    public Info setDuration(Number duration) {
        this.duration = duration;
        return this;
    }

    public Number getOwnercount() {
        return this.ownercount;
    }

    public Info setOwnercount(Number ownercount) {
        this.ownercount = ownercount;
        return this;
    }

    public Trans_param getTrans_param() {
        return this.trans_param;
    }

    public Info setTrans_param(Trans_param trans_param) {
        this.trans_param = trans_param;
        return this;
    }

    public Number getPay_type_320() {
        return this.pay_type_320;
    }

    public Info setPay_type_320(Number pay_type_320) {
        this.pay_type_320 = pay_type_320;
        return this;
    }

    public String getAlbum_name() {
        return this.album_name;
    }

    public Info setAlbum_name(String album_name) {
        this.album_name = album_name;
        return this;
    }

    public Number getOld_cpy() {
        return this.old_cpy;
    }

    public Info setOld_cpy(Number old_cpy) {
        this.old_cpy = old_cpy;
        return this;
    }

    public Number getAlbum_audio_id() {
        return this.album_audio_id;
    }

    public Info setAlbum_audio_id(Number album_audio_id) {
        this.album_audio_id = album_audio_id;
        return this;
    }

    public Number getPay_type() {
        return this.pay_type;
    }

    public Info setPay_type(Number pay_type) {
        this.pay_type = pay_type;
        return this;
    }

    public Number getSourceid() {
        return this.sourceid;
    }

    public Info setSourceid(Number sourceid) {
        this.sourceid = sourceid;
        return this;
    }

    public Number getAccompany() {
        return this.Accompany;
    }

    public Info setAccompany(Number Accompany) {
        this.Accompany = Accompany;
        return this;
    }

    public Number getPrice_320() {
        return this.price_320;
    }

    public Info setPrice_320(Number price_320) {
        this.price_320 = price_320;
        return this;
    }

    public String getTopic_url() {
        return this.topic_url;
    }

    public Info setTopic_url(String topic_url) {
        this.topic_url = topic_url;
        return this;
    }

    public Number getIsoriginal() {
        return this.isoriginal;
    }

    public Info setIsoriginal(Number isoriginal) {
        this.isoriginal = isoriginal;
        return this;
    }
}