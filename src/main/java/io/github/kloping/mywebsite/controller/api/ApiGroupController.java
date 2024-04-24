package io.github.kloping.mywebsite.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.domain.bo.PayOut;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * q群操作 需要 qqpay 的skey
 * @author github.kloping
 */
@RestController
@RequestMapping("/api/group")
public class ApiGroupController {
    @PostMapping("/pay")
    public PayOut pay(@RequestParam("skey") String skey, @RequestParam("pskey") String pskey, @RequestParam("uin") Long uin, @RequestParam("qq") Long qq, @RequestParam("select") Integer select, @RequestParam("jie") Float jie, @RequestParam("title") String title, @RequestParam("payId") String payId, @RequestParam("gid") Long gid) {
        switch (select) {
            case 1:
                return launch(pskey, skey, title, jie, qq, uin, gid);
            case 2:
                return select(pskey, skey, title, jie, qq, uin, gid, payId);
            case 3:
                return cancel(pskey, skey, title, jie, qq, uin, gid, payId);
            default:
                return null;
        }
    }

    public static final OkHttpClient CLIENT = new OkHttpClient();

    public static PayOut launch(String pskey, String skey, String title, Float jie, Long qq, Long uin, Long gid) {
        try {
            String cookie = "uin=o" + qq + "; p_uin=o" + qq + "; skey=" + skey + "; p_skey=" + pskey;
            String ua = "Mozilla/5.0 (Linux; Android 11; PCLM10 Build/RKQ1.200928.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/83.0.4103.106 Mobile Safari/537.36 V1_AND_SQ_8.8.3_1818_YYB_D A_8080300 QQ/8.8.3.5470 NetType/4G WebP/0.4.1 Pixel/1080";
            Integer m0 = (int) (jie * 200f);
            Integer m1 = (int) (jie * 100f);
            String referer = "https://mqq.tenpay.com/mqq/groupreceipts/index.shtml?uin=" + gid + "&type=4&_wv=1027&_wvx=4&from=appstore_aio";
            String url = "https://mqq.tenpay.com/cgi-bin/qcollect/qpay_collect_create.cgi?type=1&memo="
                    + title + "&amount=" + m0 + "&payer_list=[{\"uin\":\"" + qq + "\",\"amount\":\"" + m1 + "\"},{\"uin\":\"" + uin + "\",\"amount\":\"" + m1 + "\"}]&num=2&recv_type=1&group_id=" + gid + "&uin=" + qq + "&pskey=" + pskey + "&skey=" + skey + "&skey_type=2";
            Request.Builder builder = new Request.Builder().url(url)
                    .header("Cookie", cookie)
                    .header("Referer", referer)
                    .header("User-Agent", ua);
            Response response = CLIENT.newCall(builder.build()).execute();
            JSONObject jo = JSON.parseObject(response.body().string());
            return new PayOut().setCode(jo.getInteger("retcode")).setTime(System.currentTimeMillis())
                    .setText(jo.getString("retmsg")).setUin(qq).setPayuin(uin).setPayid(jo.getString("collection_no"));
        } catch (IOException e) {
            e.printStackTrace();
            return new PayOut().setCode(-1).setTime(System.currentTimeMillis())
                    .setText(e.getMessage()).setUin(qq).setPayuin(uin).setPayid("");
        }
    }

    public static PayOut select(String pskey, String skey, String title, Float jie, Long qq, Long uin, Long gid, String payId) {
        try {
            String cookie = "uin=o" + qq + "; p_uin=o" + qq + "; skey=" + skey + "; p_skey=" + pskey;
            String ua = "Mozilla/5.0 (Linux; Android 11; PCLM10 Build/RKQ1.200928.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/83.0.4103.106 Mobile Safari/537.36 V1_AND_SQ_8.8.3_1818_YYB_D A_8080300 QQ/8.8.3.5470 NetType/4G WebP/0.4.1 Pixel/1080";
            String referer = "https://mqq.tenpay.com/mqq/groupreceipts/detail.shtml?_wv=1027&_wvx=4&collectionno=" + gid;
            String url = "https://mqq.tenpay.com/cgi-bin/qcollect/qpay_collect_detail.cgi?collection_no=" + payId + "&uin=" + qq + "&pskey=" + pskey + "&skey=" + skey + "&skey_type=2";
            Request.Builder builder = new Request.Builder().url(url)
                    .header("Cookie", cookie)
                    .header("Referer", referer)
                    .header("User-Agent", ua);
            Response response = CLIENT.newCall(builder.build()).execute();
            JSONObject jo = JSON.parseObject(response.body().string());
            JSONObject jo1 = jo.getJSONArray("payer_list").getJSONObject(1);
            Integer state = jo1.getInteger("state");
            PayOut pay = new PayOut().setCode(jo.getInteger("retcode")).setTime(System.currentTimeMillis()).setUin(qq).setPayuin(uin).setPayid(jo.getString("collection_no"));
            if (state == 0) {
                pay.setText("未支付");
            } else {
                pay.setText("支付成功");
            }
            return pay;
        } catch (IOException e) {
            e.printStackTrace();
            return new PayOut().setCode(-1).setTime(System.currentTimeMillis())
                    .setText(e.getMessage()).setUin(qq).setPayuin(uin).setPayid("");
        }
    }

    public static PayOut cancel(String pskey, String skey, String title, Float jie, Long qq, Long uin, Long gid, String payId) {
        try {
            String cookie = "uin=o" + qq + "; p_uin=o" + qq + "; skey=" + skey + "; p_skey=" + pskey;
            String ua = "Mozilla/5.0 (Linux; Android 11; PCLM10 Build/RKQ1.200928.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/83.0.4103.106 Mobile Safari/537.36 V1_AND_SQ_8.8.3_1818_YYB_D A_8080300 QQ/8.8.3.5470 NetType/4G WebP/0.4.1 Pixel/1080";
            String referer = "https://mqq.tenpay.com/mqq/groupreceipts/detail.shtml?_wv=1027&_wvx=4&collectionno=" + gid;
            String url = "https://mqq.tenpay.com/cgi-bin/qcollect/qpay_collect_close.cgi?collection_no=" + payId + "&uin=" + qq + "&pskey=" + pskey + "&skey=" + skey + "&skey_type=2";
            Request.Builder builder = new Request.Builder().url(url)
                    .header("Cookie", cookie)
                    .header("Referer", referer)
                    .header("User-Agent", ua);
            Response response = CLIENT.newCall(builder.build()).execute();
            JSONObject jo = JSON.parseObject(response.body().string());
            return new PayOut().setCode(jo.getInteger("retcode")).setTime(System.currentTimeMillis())
                    .setText(jo.getString("retmsg")).setUin(qq).setPayuin(uin).setPayid(jo.getString("collection_no"));
        } catch (IOException e) {
            e.printStackTrace();
            return new PayOut().setCode(-1).setTime(System.currentTimeMillis())
                    .setText(e.getMessage()).setUin(qq).setPayuin(uin).setPayid("");
        }
    }
}
