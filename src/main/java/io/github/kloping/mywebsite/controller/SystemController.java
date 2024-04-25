package io.github.kloping.mywebsite.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


/**
 * @author github-kloping
 */
@Controller
@Slf4j
public class SystemController {

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
