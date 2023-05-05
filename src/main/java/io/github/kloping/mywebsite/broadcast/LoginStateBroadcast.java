package io.github.kloping.mywebsite.broadcast;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author github.kloping
 */
public class LoginStateBroadcast extends Broadcast<LoginStateBroadcast.LoginStateReceiver> {
    public LoginStateBroadcast() {
        super("lb");
    }

    public static final LoginStateBroadcast INSTANCE = new LoginStateBroadcast();

    public synchronized void broadcast(long qid, File qrcode, int state, String url) {
        submit(() -> {
            Iterator<LoginStateReceiver> iterator = receivers.iterator();
            while (iterator.hasNext()) {
                try {
                    LoginStateReceiver receiver = iterator.next();
                    receiver.onReceive(qid, qrcode, state, url);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    private List<LoginStateReceiver> receivers = new ArrayList<>();

    @Override
    public boolean add(LoginStateReceiver receiver) {
        return receivers.add(receiver);
    }

    @Override
    public boolean remove(LoginStateReceiver receiver) {
        return receivers.remove(receiver);
    }

    public interface LoginStateReceiver extends Receiver {
        /**
         * 登录状态
         *
         * @param qid
         * @param qrcode
         * @param state  -1 未登录
         *               0 登录中
         *               1 登录成功
         *               2 验证码
         */
        void onReceive(long qid, File qrcode, int state, String url);
    }
}
