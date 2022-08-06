package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.entitys.ApiDetailM;
import io.github.kloping.mywebsite.entitys.runcode.CodeContent;
import io.github.kloping.mywebsite.entitys.runcode.CodeEntity;
import io.github.kloping.mywebsite.plugins.Source;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author github.kloping
 */
@RestController
@RequestMapping("/api")
public class ApiToolController {
    static {
        ApiShowController.LIST.add(new ApiDetailM()
                .setName("在线运行代码")
                .setState("success")
                .setDesc("在线运行各种代码")
        );
        //=============
        ApiShowDetailController.LIST.add(
                new ApiDetail()
                        .setName("在线运行代码")
                        .setState("success")
                        .setDesc("在线运行各种代码")
                        .setAddress("/api/runCode?l=语言&file=文件名&content=文件内容&version=版本&stdin=输入")
                        .setDetail("在线运行各种代码 version 为空默认 latest ")
                        .setSimpleUrl("/api/runCode?l=c&file=main.c&content=%23include+%3Cstdio.h%3E%0A+%0Aint+main%28%29%0A%7B%0A++++printf%28%22Hello%2C+World%21+%5Cn%22%29%3B%0A++++return+0%3B%0A%7D&version=&stdin=")
        );
    }

    @GetMapping("/runCode")
    public Object run(
            @RequestParam("l") String l,
            @RequestParam("file") String f,
            @RequestParam("content") String c,
            @RequestParam("version") String v,
            @RequestParam("stdin") String i
    ) {
        CodeContent content = new CodeContent();
        content.setContent(c);
        content.setName(f);
        CodeEntity entity = new CodeEntity();
        entity.setFiles(new CodeContent[]{content});
        entity.setCommand("");
        entity.setStdin(i);
        Object o = null;
        try {
            if (v == null || v.trim().isEmpty()) {
                o = Source.runAll.runAny(entity, l);
            } else {
                o = Source.runAll.runAny(entity, l, v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return o;
    }
}
