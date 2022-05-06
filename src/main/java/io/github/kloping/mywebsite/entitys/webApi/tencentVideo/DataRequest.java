package io.github.kloping.mywebsite.entitys.webApi.tencentVideo;

import com.alibaba.fastjson.JSON;
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

    @Override
    public String toString() {
//        JSONObject jo = new JSONObject();
//        try {
//            jo.put("has_cache", has_cache);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        try {
//            jo.put("page_params", JSON.toJSONString(page_params));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return jo.toString();
        return JSON.toJSONString(this);
    }
}