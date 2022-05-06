package io.github.kloping.mywebsite.entitys.webApi.tencentVideo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * "req_from": "web",
 * "page_type": "detail_operation",
 * "page_id": "vsite_episode_list",
 * "id_type": "1",
 * "cid": "m441e3rjq9kwpsc",
 * "lid": "",
 * "vid": "m00253deqqo",
 * "page_num": "",
 * "page_size": "100",
 * "page_context": ""
 *
 * @author github-kloping
 */
public class Page_params {
    @JSONField(ordinal = 6)
    private String vid;
    @JSONField(ordinal = 2)
    private String page_id;
    @JSONField(ordinal = 0)
    private String req_from;
    @JSONField(ordinal = 1)
    private String page_type;
    @JSONField(ordinal = 5)
    private String lid;
    @JSONField(ordinal = 7)
    private String page_num;
    @JSONField(ordinal = 3)
    private String id_type;
    @JSONField(ordinal = 9)
    private String page_context;
    @JSONField(ordinal = 4)
    private String cid;
    @JSONField(ordinal = 8)
    private String page_size;

    public String getVid() {
        return this.vid;
    }

    public Page_params setVid(String vid) {
        this.vid = vid;
        return this;
    }

    public String getPage_id() {
        return this.page_id;
    }

    public Page_params setPage_id(String page_id) {
        this.page_id = page_id;
        return this;
    }

    public String getReq_from() {
        return this.req_from;
    }

    public Page_params setReq_from(String req_from) {
        this.req_from = req_from;
        return this;
    }

    public String getPage_type() {
        return this.page_type;
    }

    public Page_params setPage_type(String page_type) {
        this.page_type = page_type;
        return this;
    }

    public String getLid() {
        return this.lid;
    }

    public Page_params setLid(String lid) {
        this.lid = lid;
        return this;
    }

    public String getPage_num() {
        return this.page_num;
    }

    public Page_params setPage_num(String page_num) {
        this.page_num = page_num;
        return this;
    }

    public String getId_type() {
        return this.id_type;
    }

    public Page_params setId_type(String id_type) {
        this.id_type = id_type;
        return this;
    }

    public String getPage_context() {
        if (page_context.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append("cid=").append(cid).append("&page_num=").append(page_num)
                    .append("&page_size=").append(page_size)
                    .append("&id_type=").append(id_type)
                    .append("&req_type=6").append("")
                    .append("&req_from").append(req_from)
                    .append("&req_from_second_type=").append("")
                    .append("&detail_page_type=").append("0")
            ;
            return sb.toString();
        }
        return this.page_context;
    }

    public Page_params setPage_context(String page_context) {
        this.page_context = page_context;
        return this;
    }

    public String getCid() {
        return this.cid;
    }

    public Page_params setCid(String cid) {
        this.cid = cid;
        return this;
    }

    public String getPage_size() {
        return this.page_size;
    }

    public Page_params setPage_size(String page_size) {
        this.page_size = page_size;
        return this;
    }
}