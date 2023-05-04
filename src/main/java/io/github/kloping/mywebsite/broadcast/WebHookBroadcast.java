package io.github.kloping.mywebsite.broadcast;

import io.github.kloping.mywebsite.webhook.e0.OrderReq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author github.kloping
 */
public class WebHookBroadcast extends Broadcast<WebHookBroadcast.OrderReqReceiver> {
    public WebHookBroadcast() {
        super("webhook");
    }

    public static final WebHookBroadcast INSTANCE = new WebHookBroadcast();

    public synchronized void broadcast(OrderReq orderReq) {
        submit(() -> {
            Iterator<OrderReqReceiver> iterator = receivers.iterator();
            while (iterator.hasNext()) {
                try {
                    OrderReqReceiver receiver = iterator.next();
                    receiver.onReceive(orderReq);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    private List<OrderReqReceiver> receivers = new ArrayList<>();

    @Override
    public boolean add(OrderReqReceiver receiver) {
        return receivers.add(receiver);
    }

    @Override
    public boolean remove(OrderReqReceiver receiver) {
        return receivers.remove(receiver);
    }

    public interface OrderReqReceiver extends Receiver {
        void onReceive(OrderReq req);
    }

}
