package io.github.kloping.mywebsite.config;

import com.alibaba.fastjson2.JSON;
import io.github.kloping.mywebsite.broadcast.InterceptorBroadcast;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author github-kloping
 */
@Component
public class LogInterceptor implements HandlerInterceptor {
    private static final SimpleDateFormat DF2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒SSS");
    public static final Integer MAX = 120;
    public static final String IMPORTANT_WORD = "api";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        synchronized (DF2) {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null) ip = request.getRemoteAddr();
            ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
            System.out.print(String.format("[%s]:%s[%s](%s)", DF2.format(new Date(System.currentTimeMillis())), ip, request.getMethod(), request.getRequestURL()));
            System.out.println(JSON.toJSONString(request.getParameterMap()));
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null) ip = request.getRemoteAddr();
        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip.trim();
        InterceptorBroadcast.INSTANCE.broadcast(ip, request.getRequestURL().toString(), request.getParameterMap(), request);
    }
}
