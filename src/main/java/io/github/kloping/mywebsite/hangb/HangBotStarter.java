package io.github.kloping.mywebsite.hangb;

import io.github.kloping.common.Public;
import io.github.kloping.mywebsite.broadcast.WebHookBroadcast;
import io.github.kloping.mywebsite.webhook.e0.OrderReq;

/**
 * @author github.kloping
 */
public class HangBotStarter {
    public static void main(String[] args) {
        Public.EXECUTOR_SERVICE.submit(() -> MiraiConsoleStarter.main(args));
        WebHookBroadcast.INSTANCE.add(HangBotStarter::onReceive);
    }

    public static void onReceive(OrderReq req) {
        System.out.println("handler req => " + req);
        if (req == null) return;
        try {
            if (!req.getData().getOrder().getPlan_title().equals("腐竹")) {
                return;
            }
        } catch (Exception e) {

        }
    }
}
