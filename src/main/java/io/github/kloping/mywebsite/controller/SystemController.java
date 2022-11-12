package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSONObject;
import io.github.kloping.io.ReadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index(@RequestHeader(value = "User-Agent") String userAgent) {
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
