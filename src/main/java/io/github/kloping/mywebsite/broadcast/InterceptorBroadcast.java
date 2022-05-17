package io.github.kloping.mywebsite.broadcast;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author github.kloping
 */
public class InterceptorBroadcast extends Broadcast {
    public InterceptorBroadcast() {
        super("i0");
    }

    public static final InterceptorBroadcast INSTANCE = new InterceptorBroadcast();

    public synchronized void broadcast(String ip, String url, Map<String, String[]> map, HttpServletRequest request) {
        Iterator<InterceptorReceiver> iterator = receivers.iterator();
        while (iterator.hasNext()) {
            try {
                InterceptorReceiver receiver = iterator.next();
                if (receiver.onReceive(ip, url, map, request) && receiver instanceof OnceInterceptorReceiver) {
                    iterator.remove();
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private List<InterceptorReceiver> receivers = new ArrayList<>();

    @Override
    public boolean add(Receiver receiver) {
        if (receiver instanceof InterceptorReceiver) {
            return receivers.add((InterceptorReceiver) receiver);
        }
        return false;
    }

    @Override
    public boolean remove(Receiver receiver) {
        return receivers.remove(receiver);
    }

    public static interface InterceptorReceiver extends Receiver {
        /**
         * on receive
         *
         * @param ip
         * @param url
         * @param map
         * @param request
         * @return
         */
        boolean onReceive(String ip, String url, Map<String, String[]> map, HttpServletRequest request);
    }

    public static interface OnceInterceptorReceiver extends InterceptorReceiver {
    }
}
