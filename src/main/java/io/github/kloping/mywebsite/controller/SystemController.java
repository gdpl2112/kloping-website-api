package io.github.kloping.mywebsite.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static io.github.kloping.mywebsite.Source.println;


/**
 * @author github-kloping
 */
@Controller
@Slf4j
public class SystemController {
    public static int AllNum = 1;

    public SystemController() {
        println(this.getClass().getSimpleName() + "构建");
    }

    @GetMapping({"/", "/#"})
    public String index(HttpServletRequest request, HttpServletResponse response, @RequestHeader(value = "User-Agent") String userAgent) {
        return "redirect:index.html";
    }

    @GetMapping("/favicon.ico")
    public String faviconIco() {
        return "redirect:http://q1.qlogo.cn/g?b=qq&nk=3474006766&s=640";
    }

    @GetMapping("/api")
    public String apiList(@RequestHeader(value = "User-Agent") String userAgent) {
        return "redirect:api.html";
    }
}
