package io.github.kloping.mywebsite.config;

import io.github.kloping.mywebsite.broadcast.InterceptorBroadcast;
import io.github.kloping.mywebsite.controller.SystemController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author github-kloping
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
    private static final SimpleDateFormat DF2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒SSS");
    public static final Integer MAX = 120;
    public static final String IMPORTANT_WORD = "api";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null) ip = request.getRemoteAddr();
        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip.trim();
        String url = request.getRequestURL().toString();
        System.out.println(String.format("[%s]: %s 访问了 Path:%s", DF2.format(new Date(System.currentTimeMillis())), ip, url));
        request.getParameterMap().forEach((k, v) -> {
            System.out.print(", ");
            System.out.print(k + "=>" + Arrays.toString(v));
        });
        System.out.println();
        if (url != null) {
            if (url.contains(IMPORTANT_WORD)) {
                if (whiteListIp.contains(ip)) return true;
                Integer i = MAPS.get(ip);
                i = i == null ? 1 : i;
                i++;
                MAPS.put(ip, i);
                if (i >= MAX) {
                    response.setStatus(486);
                    return false;
                }
            }
        }
        reg(request, response);
        return true;
    }


    static {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                MAPS.forEach((k, v) -> {
                    v -= 10;
                    MAPS.put(k, v < 1 ? 1 : v);
                });
            }
        }, 60000, 60000);
    }

    public static List<String> whiteListIp = new CopyOnWriteArrayList<>();

    public static final Map<String, Integer> MAPS = new ConcurrentHashMap<>();

    static {
        whiteListIp.add("49.232.209.180");
        whiteListIp.add("120.242.148.86");
    }

    private void reg(HttpServletRequest request, HttpServletResponse response) {
        SystemController.AllNum += 1;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null) ip = request.getRemoteAddr();
        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip.trim();
        String url = request.getRequestURL().toString();
        InterceptorBroadcast.INSTANCE.broadcast(ip, url, request.getParameterMap(),request);
    }

}
