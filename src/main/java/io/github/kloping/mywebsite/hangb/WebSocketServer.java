package io.github.kloping.mywebsite.hangb;

import com.alibaba.fastjson.JSON;
import io.github.kloping.common.Public;
import io.github.kloping.file.FileUtils;
import io.github.kloping.mywebsite.broadcast.LoginStateBroadcast;
import io.github.kloping.mywebsite.controller.UtilsController;
import io.github.kloping.mywebsite.hangb.entity.VerifyQ1;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.IOException;

/**
 * @author github-kloping
 */
@ServerEndpoint(value = "/hangws")
@Component
@Slf4j
public class WebSocketServer implements LoginStateBroadcast.LoginStateReceiver {
    //数据异常
    public static final int DATA_ERR = -12;
    //授权过期
    public static final int AUTH_EXP = -15;
    //未知登录方式
    public static final int UNKNOWN_LOGIN_TYPE = -13;
    //登录中...
    public static final int LOGIN_STATE = -14;
    //等待中...
    public static final int NULL_STATE = -1;
    //初始化数据
    public static final int INIT_DATA = 1;
    //登录失败
    public static final int LOGIN_FAILED = 500;
    //登录成功
    public static final int LOGIN_SUCCESS = 200;
    //扫码
    public static final int LOGIN_STATE1 = 201;
    //验证码
    public static final int LOGIN_STATE2 = 202;


    public WebSocketServer() {
        System.out.println("create one wss");
        LoginStateBroadcast.INSTANCE.add(this);
    }

    public VerifyQ1 vq1 = null;
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnClose
    public void onClose() {
        LoginStateBroadcast.INSTANCE.remove(this);
        if (vq1 != null) {
            Bot bot = Bot.getInstance(vq1.getQid());
            if (!bot.isOnline())
                bot.close();
        }
    }

    public int state = NULL_STATE;

    @Override
    public void onReceive(long qid, File qrcode, int state, String url) {
        if (vq1 == null) return;
        if (qid == vq1.getQid()) {
            //-1 未登录 0 登录中 1 登录成功 2 验证码
            try {
                switch (state) {
                    case -1:
                        session.getBasicRemote().sendText(String.valueOf(LOGIN_FAILED));
                        break;
                    case 0:
                        if (qrcode != null) {
                            session.getBasicRemote().sendText(String.valueOf(LOGIN_STATE1));
                            String u0 = UtilsController.save(FileUtils.getBytesFromFile(qrcode.getAbsolutePath()), true);
                            session.getBasicRemote().sendText("/" + u0);
                            qrcode.delete();
                        }
                        break;
                    case 1:
                        session.getBasicRemote().sendText(String.valueOf(LOGIN_SUCCESS));
                        HangBotStarter.save(vq1);
                        break;
                    case 2:
                        session.getBasicRemote().sendText(String.valueOf(LOGIN_STATE1));
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        System.out.println(message);
        if (state == NULL_STATE) {
            state = Integer.valueOf(message);
            return;
        }
        switch (state) {
            case INIT_DATA:
                vq1 = JSON.parseObject(message).toJavaObject(VerifyQ1.class);
                Public.EXECUTOR_SERVICE.submit(() -> {
                    try {
                        Integer state = HangBotStarter.dispose(vq1);
                        session.getBasicRemote().sendText(state.toString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                session.getBasicRemote().sendText(String.valueOf(LOGIN_STATE));
                break;
        }
        state = -1;
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误", error);
        error.printStackTrace();
    }
}


