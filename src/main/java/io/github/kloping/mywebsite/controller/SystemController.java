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

    @Value("${auth.pwd}")
    String pwd;

    @GetMapping("/exec")
    public Object exec(@RequestParam("line") String line, @RequestParam("pwd") String pwd) {
        if (pwd.equals(this.pwd)) {
            try {
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec(line);
                process.waitFor();
                String i0 = ReadUtils.readAll(process.getInputStream(), "utf-8");
                String e0 = ReadUtils.readAll(process.getErrorStream(), "utf-8");
                JSONObject jo = new JSONObject();
                jo.put("in", i0);
                jo.put("err", e0);
                return jo;
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else return "error";
    }
}
