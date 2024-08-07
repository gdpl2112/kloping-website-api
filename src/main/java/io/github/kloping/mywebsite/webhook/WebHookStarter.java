package io.github.kloping.mywebsite.webhook;

/**
 * @author github.kloping
 */
public class WebHookStarter implements Runnable {
    @Override
    public void run() {
        try {
            WebHookServer.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
    public void onReceive(OrderReq req) {
     if (req == null) return;
        try {
            if (!req.getData().getOrder().getPlan_title().equals("腐竹")) {
                return;
            }
            String amount = req.getData().getOrder().getTotal_amount().trim();
            if (amount == null || amount.isEmpty() || amount.equals("0.00")) {
                amount = req.getData().getOrder().getShow_amount().trim();
            }
            String remark = req.getData().getOrder().getRemark().trim();
            if (remark == null || remark.isEmpty()) {
                remark = req.getData().getOrder().getAddress_address();
            }
            Integer month = req.getData().getOrder().getMonth().intValue();

            if (url == null)
                url = KlopingBlogApplication.applicationContext.getEnvironment().getProperty("auth.url").toString();
            if (pwd == null)
                pwd = KlopingBlogApplication.applicationContext.getEnvironment().getProperty("auth.pwd").toString();

            Integer j = Double.valueOf(amount).intValue();
            Long qid = Long.valueOf(remark);

            StringBuilder sb = new StringBuilder();
            sb.append("<At:").append(qid).append(">.\n").append("您的");
            Integer sc = 0;
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
                default:
                    break;
            }
            sc *= month;
            sb.append("*").append(month).append("积分已到账\n感谢您对`爱发电`的支持\n感谢您的充值");
            UrlUtils.getStringFromHttpUrl(url + "/addScore?qid=" + qid + "&pwd=" + pwd + "&s=" + (sc * 10000));
            UrlUtils.getStringFromHttpUrl(url + "/say?gid=278681553&pwd=" + pwd + "&s=" + URLEncoder.encode(sb.toString()));
        } catch (Exception e) {
            System.err.println("订单信息错误");
            throw new RuntimeException(e);
     }
     }*/
}
