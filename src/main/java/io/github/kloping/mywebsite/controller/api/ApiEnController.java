package io.github.kloping.mywebsite.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.domain.po.BottleMessage;
import io.github.kloping.mywebsite.domain.po.Illegal;
import io.github.kloping.mywebsite.mapper.BottleMessageMapper;
import io.github.kloping.mywebsite.mapper.IllegalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 * @author github.kloping
 */
@RestController
@RequestMapping("/api")
public class ApiEnController {

    @Autowired
    BottleMessageMapper bottleMessageMapper;

    @Autowired
    IllegalMapper illegalMapper;

    @GetMapping("/throwBottle")
    public Object throwBottle(@RequestParam("gid") String gid, @RequestParam("sid") String sid, @RequestParam("message") String message, @RequestParam("name") @Nullable String name) {
        if (gid == null || sid == null || message == null || message.isEmpty()) return "参数不能为空";
        BottleMessage bottle = new BottleMessage();
        bottle.setGid(gid).setSid(sid).setMessage(message).setName(name).setTime(System.currentTimeMillis()).setState(0);
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
        System.out.println(max);
        List<BottleMessage> list = bottleMessageMapper.selectList(queryWrapper);
        if (list.isEmpty()) {
            bottle = new BottleMessage();
            bottle.setName("默认昵称").setId(0).setGid("0").setSid("0").setTime(System.currentTimeMillis()).setState(0).setId(-1).setMessage("空瓶子");
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
}
