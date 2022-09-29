package io.github.kloping.mywebsite.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 方法1：Spring Boot 将所有的错误默认映射到/error， 实现ErrorController
 *
 * @author cjj
 */
@Controller
@RequestMapping("/error")
public class BaseErrorPage implements ErrorController {

    public String getErrorPath() {
        return "/error.html";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}