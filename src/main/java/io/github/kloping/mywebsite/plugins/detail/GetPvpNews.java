package io.github.kloping.mywebsite.plugins.detail;

import com.alibaba.fastjson.JSON;
import io.github.kloping.MySpringTool.annotations.AutoStand;
import io.github.kloping.MySpringTool.annotations.Entity;
import io.github.kloping.MySpringTool.entity.ParamsBuilder;
import io.github.kloping.mywebsite.entitys.pvpQqCom.Response0;
import io.github.kloping.mywebsite.entitys.pvpQqCom.v1.Response1;
import io.github.kloping.mywebsite.plugins.interfaces.GetPvpQq;
import io.github.kloping.number.NumberUtils;
import io.github.kloping.url.UrlUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author github-kloping
 */
@Entity
public class GetPvpNews {

    @AutoStand
    private GetPvpQq getPvpQQ;

    public Response0 m1() {
        String urlStr = UrlUtils.getStringFromHttpUrl("https://game.gtimg.cn/images/yxzj/web201706/js/newsindex.js");
        String[] sss = urlStr.split("\r|\n");
        String source = "web_pc";
        int serviceId = Integer.parseInt(NumberUtils.findNumberFromString(sss[11]));
        int i1 = sss[12].indexOf("\'");
        int i2 = sss[12].lastIndexOf("\'");
        String token = sss[12].substring(i1 + 1, i2);
        int id = Integer.parseInt(NumberUtils.findNumberFromString(sss[13]));
        String sign = makeSign(source, serviceId, id, token);
        Response0 data = getPvpQQ.get(new ParamsBuilder()
                .append("serviceId", String.valueOf(serviceId))
                .append("filter", "channel")
                .append("sortby", "sIdxTime")
                .append("source", "web_pc")
                .append("limit", "12")
                .append("logic", "or")
                .append("typeids", "1")
                .append("chanid", "1762")
                .append("start", "0")
                .append("withtop", "yes")
                .append("exclusiveChannel", "4")
                .append("exclusiveChannelSign", sign)
                .append("time", System.currentTimeMillis() / 1000 + "")
                .build());
        return data;
    }


    public String makeSign(String source, int serviceId, int id, String token) {
        long timestamp = System.currentTimeMillis() / 1000;
        String sign = md5(token + source + serviceId + timestamp);
        return sign;
    }

    public String md5(String data) {
        try {
            byte[] md5 = md5(data.getBytes("utf-8"));
            return toHexString(md5);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public byte[] md5(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            return md.digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    private String toHexString(byte[] md5) {
        StringBuilder sb = new StringBuilder();
        for (byte b : md5) {
            sb.append(Integer.toHexString(b & 0xff));
        }
        return sb.toString();
    }

    public Response1 getNews(Long id) {
        if (id == null) return null;
        String ss = getPvpQQ.get0(18, "web_pc", id);
        int i1 = ss.indexOf("var searchObj=");
        int i2 = ss.lastIndexOf(";");
        ss = ss.substring(i1 + "var searchObj=".length(), i2);
        Response1 r1 = JSON.parseObject(ss).toJavaObject(Response1.class);
        return r1;
    }
}
