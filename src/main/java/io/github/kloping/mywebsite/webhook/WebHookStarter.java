package io.github.kloping.mywebsite.webhook;

import io.github.kloping.mywebsite.MyWebSiteApplication;
import io.github.kloping.mywebsite.broadcast.WebHookBroadcast;
import io.github.kloping.mywebsite.webhook.e0.OrderReq;
import io.github.kloping.url.UrlUtils;

import java.net.URLEncoder;

/**
 * @author github.kloping
 */
public class WebHookStarter implements Runnable, WebHookBroadcast.OrderReqReceiver {
    @Override
    public void run() {
        try {
            Server.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        WebHookBroadcast.INSTANCE.add(this);
    }

    String url;
    String pwd;

    @Override
    public void onReceive(OrderReq req) {
        if (req == null) return;
        String amount = req.getData().getOrder().getTotal_amount().trim();
        String remark = req.getData().getOrder().getRemark().trim();
        if (url == null)
            url = MyWebSiteApplication.applicationContext.getEnvironment().getProperty("auth.url").toString();
        if (pwd == null)
            pwd = MyWebSiteApplication.applicationContext.getEnvironment().getProperty("auth.pwd").toString();
        try {
            Integer sc = 0;
            Integer j = Double.valueOf(amount).intValue();
            Long qid = Long.valueOf(remark);
            StringBuilder sb = new StringBuilder();
            sb.append("<At:").append(qid).append(">\n").append("您的");
            switch (j) {
                case 5:
                    sb.append("33w");
                    sc = 33;
                    break;
                case 10:
                    sb.append("66w");
                    sc = 66;
                    break;
                case 20:
                    sb.append("127w");
                    sc = 127;
                    break;
            }
            sb.append("积分已到账\n感谢您对`爱发电`的支持\n感谢您的充值");

            UrlUtils.getStringFromHttpUrl(url + "/addScore?qid=" + qid + "&pwd=" + pwd + "&s=" + (sc * 10000));
            UrlUtils.getStringFromHttpUrl(url + "/say?gid=278681553&pwd=" + pwd + "&s=" + URLEncoder.encode(sb.toString()));

        } catch (NumberFormatException e) {
            System.err.println("订单信息错误");
            throw new RuntimeException(e);
        }
    }
}
