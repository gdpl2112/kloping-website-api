package io.github.kloping.mywebsite.entitys.webApi.tencentVideo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author github-kloping
 */
public class DataRequest {
    @JSONField(ordinal = 1)
    private Number has_cache;

    private Page_params page_params = new Page_params();

    public Number getHas_cache() {
        return this.has_cache;
    }

    public DataRequest setHas_cache(Number has_cache) {
        this.has_cache = has_cache;
        return this;
    }

    public Page_params getPage_params() {
        return this.page_params;
    }

    public DataRequest setPage_params(Page_params page_params) {
        this.page_params = page_params;
        return this;
    }

    //  "req_from": "web",
    //    "cid": "m441e3rjq9kwpsc",
    //    "page_id": "pc_short_video_infinite_rec",
    //    "page_type": "detail_operation",
    //    "lid": ""

    //    "req_from": "web",
    //    "cid": "q.com",
    //    "lid": "",

    @Override
    public String toString() {
        JSONObject jo = JSON.parseObject(JSON.toJSONString(this));

//        jo.getJSONObject("page_params").remove("vid");
//        jo.getJSONObject("page_params").remove("page_num");
//        jo.getJSONObject("page_params").remove("page_size");
//        jo.getJSONObject("page_params").remove("page_context");
//        jo.getJSONObject("page_params").put("page_id", "pc_short_video_infinite_rec");
//        jo.getJSONObject("page_params").put("page_type", "detail_operation");
        return jo.toString();
    }
}