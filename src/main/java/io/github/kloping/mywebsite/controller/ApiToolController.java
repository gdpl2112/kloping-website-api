package io.github.kloping.mywebsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.entitys.ApiDetailM;
import io.github.kloping.mywebsite.entitys.BottleMessage;
import io.github.kloping.mywebsite.entitys.Illegal;
import io.github.kloping.mywebsite.entitys.runcode.CodeContent;
import io.github.kloping.mywebsite.entitys.runcode.CodeEntity;
import io.github.kloping.mywebsite.mapper.BottleMessageMapper;
import io.github.kloping.mywebsite.mapper.IllegalMapper;
import io.github.kloping.mywebsite.plugins.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

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
        ApiShowDetailController.LIST.add(
                new ApiDetail()
                        .setName("在线运行代码")
                        .setState("success")
                        .setDesc("在线运行各种代码")
                        .setAddress("/api/runCode?l=语言&file=文件名&content=文件内容&version=版本&stdin=输入")
                        .setDetail("在线运行各种代码 version 为空默认 latest ")
                        .setSimpleUrl("/api/runCode?l=c&file=main.c&content=%23include+%3Cstdio.h%3E%0A+%0Aint+main%28%29%0A%7B%0A++++printf%28%22Hello%2C+World%21+%5Cn%22%29%3B%0A++++return+0%3B%0A%7D&version=&stdin=")
        );
        //=============
        ApiShowController.LIST.add(new ApiDetailM()
                .setName("扔漂流瓶")
                .setState("success")
                .setDesc("扔出一个漂流瓶")
        );
        ApiShowDetailController.LIST.add(
                new ApiDetail()
                        .setName("扔漂流瓶")
                        .setState("success")
                        .setDesc("扔出一个漂流瓶")
                        .setAddress("/api/throwBottle?sid=qq&gid=群号&message=内容&name=名字可无")
                        .setDetail("扔出一个漂流瓶(自带非法字符检测) 请自行测试")
                        .setSimpleUrl("/ok")
        );
        //=============
        ApiShowController.LIST.add(new ApiDetailM()
                .setName("捡漂流瓶")
                .setState("success")
                .setDesc("捡一个漂流瓶")
        );
        ApiShowDetailController.LIST.add(
                new ApiDetail()
                        .setName("捡漂流瓶")
                        .setState("success")
                        .setDesc("捡一个漂流瓶")
                        .setAddress("/api/pickUpBottle")
                        .setDetail("捡一个漂流瓶 每个漂流瓶只能被捡3-5次 自行测试")
                        .setSimpleUrl("/ok")
        );
    }

    @Autowired
    BottleMessageMapper bottleMessageMapper;

    @Autowired
    IllegalMapper illegalMapper;

    @GetMapping("/throwBottle")
    public Object throwBottle(
            @RequestParam("gid") Long gid,
            @RequestParam("sid") Long sid,
            @RequestParam("message") String message,
            @RequestParam("name") @Nullable String name
    ) {
        if (gid == null || sid == null || message == null || message.isEmpty())
            return "参数不能为空";
        BottleMessage bottle = new BottleMessage();
        bottle.setGid(gid).setSid(sid).setMessage(message).setName(name).setTime(System.currentTimeMillis())
                .setState(0);
        for (Illegal illegal : illegalMapper.selectAll()) {
            if (message.contains(illegal.getContent())) return "经系统检测该内容存在敏感字符,不得扔入大海";
        }
        bottleMessageMapper.insert(bottle);
        return "您的漂流瓶已扔入大海,等待有缘人的拾取";
    }

    @Value("${bottle.max.pickup}")
    Integer max;

    public static Random RANDOM = new SecureRandom();

    @GetMapping("/pickUpBottle")
    public Object throwBottle() {
        BottleMessage bottle = null;
        QueryWrapper<BottleMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("state", max);
        List<BottleMessage> list = bottleMessageMapper.selectList(queryWrapper);
        if (list.isEmpty()) {
            bottle = new BottleMessage();
            bottle.setName("默认昵称").setId(0).setGid(0L).setSid(0L).setTime(System.currentTimeMillis())
                    .setState(0).setId(-1).setMessage("空瓶子");
            return bottle;
        }
        Integer r = RANDOM.nextInt(list.size());
        bottle = list.get(r);
        try {
            return bottle;
        } finally {
            bottle.setState(bottle.getState() + 1);
            bottleMessageMapper.updateById(bottle);
        }
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
