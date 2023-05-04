package io.github.kloping.mywebsite.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 方法1：Spring Boot 将所有的错误默认映射到/error， 实现ErrorController
 *
 * @author cjj
 */
@RestController
@RequestMapping("/error")
public class BaseErrorPage implements ErrorController {

    public String getErrorPath() {
        return "/error.html";
    }

    @RequestMapping
    public void error(HttpServletResponse response) throws IOException {
        response.sendRedirect(getErrorPath());
    }
}