package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.pay.OrderReturn;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author github.kloping
 */
@RestController
public class PayController {

    public static final Map<Integer, Integer> Q2C = new HashMap<>();

    static {
        Q2C.put(1, 52000);
        Q2C.put(2, 110000);
        Q2C.put(3, 160000);
        Q2C.put(5, 315000);
        Q2C.put(10, 625000);
        Q2C.put(20, 1205000);
    }

    public List<OrderReturn> orderReturns = new ArrayList<>();

    public Map<String, OrderReturn> sign2or = new HashMap<>();

    public final long T0 = 1000L * 60 * 10;

    {
        UtilsController.NOTICES.add((p, t, c) -> {
            if ("com.eg.android.AlipayGphone".equals(p)) {
                if (t.startsWith("你已成功收款")) {
                    String m = t.substring(7, t.length() - 1);
                    Float m0 = Float.valueOf(m);
                    System.out.println(m + "=>" + m0);
                    for (OrderReturn orderReturn : orderReturns) {
                        Float f0 = Float.valueOf(orderReturn.getMoney());
                        System.out.println(f0);
                        if (m0.intValue() == f0.intValue()) {
                            String url0 = String.format(
                                    "http://49.232.209.180:20049/addScore?qid=" + orderReturn.getQid() + "&pwd=4432120&s="
                                            + Q2C.get(f0.intValue()));
                            System.out.println(url0);
                            try {
                                new URL(url0).openStream();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    @Scheduled(cron = "0 0/5 0 * * ?")
    public void m0() {
        Iterator<OrderReturn> iterator = orderReturns.iterator();
        while (iterator.hasNext()) {
            OrderReturn or = iterator.next();
            if (or.getTime() - System.currentTimeMillis() > T0) {
                iterator.remove();
            }
        }
    }

    @GetMapping("/pay/order")
    public Object order(String aid, String qid, String money) {
        String sign = qid + aid + money;
        if (sign2or.containsKey(sign)) return sign2or.get(sign);
        OrderReturn ord = new OrderReturn();
        ord.setAid(aid);
        ord.setQid(qid);
        ord.setMoney(money);
        ord.setOrderId(System.currentTimeMillis() + qid + money + qid);
        sign2or.put(sign, ord);
        return ord.summon();
    }
}
