package io.github.kloping.mywebsite;

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
    private static final SimpleDateFormat df2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒SSS");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null) ip = request.getRemoteAddr();
        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip.trim();
        System.out.println(String.format("[%s]: %s 访问了 Path:%s",
                df2.format(new Date(System.currentTimeMillis())), ip, request.getRequestURL()));
        request.getParameterMap().forEach((k, v) -> {
            System.out.print(", ");
            System.out.print(k + "=>" + Arrays.toString(v));
        });
        System.out.println();
        String url = request.getRequestURL().toString();
        if (url != null) {
            if (url.endsWith("abo")) {
                return true;
            }
            if (url.contains("/api")) {
                if (whiteListIp.contains(ip)) return true;
                Integer i = maps.get(ip);
                i = i == null ? 1 : i;
                i++;
                maps.put(ip, i);
                if (i >= 120) {
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
                maps.forEach((k, v) -> {
                    v -= 10;
                    maps.put(k, v < 1 ? 1 : v);
                });
            }
        }, 60 * 1000, 60 * 1000);
    }

    public static List<String> whiteListIp = new CopyOnWriteArrayList<>();

    public static final Map<String, Integer> maps = new ConcurrentHashMap<>();

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
    }

}
