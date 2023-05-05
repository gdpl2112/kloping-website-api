package io.github.kloping.mywebsite.hangb;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.common.Public;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.MyWebSiteApplication;
import io.github.kloping.mywebsite.broadcast.LoginStateBroadcast;
import io.github.kloping.mywebsite.broadcast.WebHookBroadcast;
import io.github.kloping.mywebsite.hangb.entity.VerifyQ;
import io.github.kloping.mywebsite.hangb.entity.VerifyQ1;
import io.github.kloping.mywebsite.mapper.QVMapper;
import io.github.kloping.mywebsite.webhook.e0.OrderReq;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.BotOnlineEvent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.concurrent.CountDownLatch;

/**
 * @author github.kloping
 */
public class HangBotStarter extends SimpleListenerHost {
    public static final File TEMP_DIR = new File(System.getProperty("java.io.tmpdir"));
    public static CountDownLatch CDL = new CountDownLatch(1);

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }

    @EventHandler
    public void onEvent(BotOnlineEvent event) {
        LoginStateBroadcast.INSTANCE.broadcast(event.getBot().getId(), null, 1, "");
    }

    public static void main(String[] args) {
        Public.EXECUTOR_SERVICE.submit(() -> MiraiConsoleStarter.main(args));
        GlobalEventChannel.INSTANCE.registerListenerHost(new HangBotStarter());
        WebHookBroadcast.INSTANCE.add(HangBotStarter::onReceive);
        Public.EXECUTOR_SERVICE.submit(HangBotStarter::load);
        for (File file : TEMP_DIR.listFiles((f, n) -> n.contains("mirai-qrcode-"))) {
            file.delete();
        }
    }

    public static QVMapper QVM;

    public static void load() {
        try {
            CDL.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        QVM = MyWebSiteApplication.applicationContext.getBean(QVMapper.class);
        for (VerifyQ verifyQ : QVM.selectList(new QueryWrapper<>())) {
            dispose(verifyQ);
        }
    }

    public static Integer dispose(VerifyQ verifyQ) {
        if (Judge.isEmpty(verifyQ.getProtocol())) return WebSocketServer.AUTH_EXP;
        if (verifyQ.getType() < 0) return WebSocketServer.AUTH_EXP;
        Bot bot = Bot.getInstanceOrNull(verifyQ.getQid());
        if (bot != null) bot.close();
        if (verifyQ.getExpire() > System.currentTimeMillis()) {
            switch (verifyQ.getType()) {
                case 1:
                    loginByPwd(verifyQ);
                    break;
                case 2:
                    loginByQr(verifyQ);
                    break;
                default:
                    System.out.println("未知登录方式");
                    return WebSocketServer.UNKNOWN_LOGIN_TYPE;
            }
        } else {
            System.out.println(verifyQ.getQid() + "=>已过期");
            return WebSocketServer.AUTH_EXP;
        }
        return WebSocketServer.LOGIN_STATE;
    }

    public static Integer dispose(VerifyQ1 vq1) {
        VerifyQ vq = new VerifyQ();
        vq.setQid(vq1.getQid());
        switch (vq1.getStype()) {
            case "扫码":
                vq.setType(2);
                break;
            case "密码":
                vq.setType(1);
        }
        vq.setProtocol(vq1.getProtocol());
        vq.setPwd(vq1.getPwd());
        VerifyQ vvq = QVM.selectById(vq1.getQid());
        if (vvq == null) return WebSocketServer.AUTH_EXP;
        else vq.setExpire(vvq.getExpire());
        return dispose(vq);
    }

    public static void save(VerifyQ1 vq1) {
        VerifyQ vq = new VerifyQ();
        vq.setQid(vq1.getQid());
        switch (vq1.getStype()) {
            case "扫码":
                vq.setType(2);
                break;
            case "密码":
                vq.setType(1);
        }
        vq.setProtocol(vq1.getProtocol());
        vq.setPwd(vq1.getPwd());
        VerifyQ vvq = QVM.selectById(vq1.getQid());
        if (vvq == null) return;
        else vq.setExpire(vvq.getExpire());
        QVM.updateById(vq);
    }

    public static void loginByQr(VerifyQ vq) {
        Public.EXECUTOR_SERVICE.submit(() -> {
            MiraiConsoleStarter.loginByQr(vq);
        });
        Public.EXECUTOR_SERVICE.submit(() -> {
            while (true) {
                File[] files = TEMP_DIR.listFiles((f, n) -> n.contains("mirai-qrcode-" + vq.getQid()));
                if (files.length != 0) {
                    File file = files[0];
                    LoginStateBroadcast.INSTANCE.broadcast(vq.getQid(), file, 0, "");
                    break;
                }
            }
        });
    }

    private static void loginByPwd(VerifyQ verifyQ) {
        if (Judge.isNotEmpty(verifyQ.getPwd())) {
            Public.EXECUTOR_SERVICE.submit(() -> MiraiConsoleStarter.loginByPwd(verifyQ));
        }
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
