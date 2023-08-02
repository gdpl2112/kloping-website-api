package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.entitys.AddressCode;
import io.github.kloping.mywebsite.entitys.database.BottleMessage;
import io.github.kloping.mywebsite.entitys.database.Illegal;
import io.github.kloping.mywebsite.entitys.medias.position.PositionInfo;
import io.github.kloping.mywebsite.entitys.runcode.CodeContent;
import io.github.kloping.mywebsite.entitys.runcode.CodeEntity;
import io.github.kloping.mywebsite.entitys.yuanShen.YuanShenPlayerInfo;
import io.github.kloping.mywebsite.mapper.AddressCodeMapper;
import io.github.kloping.mywebsite.mapper.BottleMessageMapper;
import io.github.kloping.mywebsite.mapper.IllegalMapper;
import io.github.kloping.mywebsite.plugins.Source;
import io.github.kloping.mywebsite.services.IgetLngLat;
import io.github.kloping.mywebsite.utils.ImageDrawer;
import io.github.kloping.mywebsite.utils.MyUtils;
import io.github.kloping.url.UrlUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author github.kloping
 */
@RestController
@RequestMapping("/api")
public class ApiToolController {

    @RequestMapping("/ocr")
    public List<String> ocr(@RequestParam("url") @Nullable String url, @RequestParam("file") @Nullable MultipartFile imageFile) {
        try {
            byte[] bytes = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                bytes = imageFile.getBytes();
            } else if (Judge.isNotEmpty(url)) {
                bytes = UrlUtils.getBytesFromHttpUrl(url);
            } else return new ArrayList<>();
            String json = Jsoup.connect("http://www.iinside.cn:7001/api_req/").method(Connection.Method.POST).ignoreContentType(true).ignoreHttpErrors(true).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.78").header("Accept", "application/json, */*").data("image_ocr_pp", "wx.png", new ByteArrayInputStream(bytes), "application/octet-stream").data("password", "8907").data("reqmode", "ocr_pp").execute().body();
            List<String> list = new ArrayList<>();
            for (Object o : JSON.parseObject(json).getJSONArray("data")) {
                list.add(o.toString());
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Autowired
    BottleMessageMapper bottleMessageMapper;

    @Autowired
    IllegalMapper illegalMapper;

    @GetMapping("/throwBottle")
    public Object throwBottle(@RequestParam("gid") Long gid, @RequestParam("sid") Long sid, @RequestParam("message") String message, @RequestParam("name") @Nullable String name) {
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
            bottle.setName("默认昵称").setId(0).setGid(0L).setSid(0L).setTime(System.currentTimeMillis()).setState(0).setId(-1).setMessage("空瓶子");
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
    public Object run(@RequestParam("l") String l, @RequestParam("file") String f, @RequestParam("content") String c, @RequestParam("version") String v, @RequestParam("stdin") String i) {
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

    private static final Map<String, String> HEADERS0 = new HashMap<>();

    static {
        HEADERS0.put("origin", "https://ys.daidr.me");
        HEADERS0.put("referer", "https://ys.daidr.me/");
        HEADERS0.put("accept", "*/*");
    }

    @GetMapping("/shenInfo")
    public String info(@RequestParam("uid") String uid, @RequestParam("server") Integer server, HttpServletRequest request, HttpServletResponse response) throws Exception {
        YuanShenPlayerInfo info = Source.daidr.info(uid, server, HEADERS0);
        String name = ImageDrawer.drawerShenInfo(info);
        return "http://kloping.top/" + name;
    }

    @Autowired
    AddressCodeMapper acMapper;

    @Autowired
    IgetLngLat igetLngLat;

    @RequestMapping("/acode")
    public Object aCode(@RequestParam("name") String name) {
        QueryWrapper<AddressCode> qw = new QueryWrapper<>();
        qw.likeRight("c_name", name);
        List<AddressCode> list = acMapper.selectList(qw);
        if (!list.isEmpty()) return list.get(0);
        else {
            try {
                PositionInfo info = igetLngLat.get(name);
                return info.getDetail().getAdcode();
            } catch (Exception e) {
                e.printStackTrace();
                return "{}";
            }
        }
    }

    @Autowired
    UtilsController utilsController;

    private static final String SLEEP_KEY = "sleep0";

    @RequestMapping("/sleep/start")
    public Object sleepStart(@RequestParam("id") String id) {
        JSONObject jo = new JSONObject();
        String out = utilsController.put(id, String.valueOf(System.currentTimeMillis()), SLEEP_KEY);
        if (out == null) {
            jo.put("start", 1);
            jo.put("desc", "已记录开始时间!");
        } else {
            jo.put("start", 2);
            jo.put("desc", "已重新记录开始时间!");
        }
        jo.put("state", 200);
        return jo;
    }

    @RequestMapping("/sleep/end")
    public String sleepEnd(@RequestParam("id") String id) {
        JSONObject jo = new JSONObject();
        String time0 = utilsController.get(id, SLEEP_KEY);
        if (Judge.isEmpty(time0)) return "未记录开始时间,无法计算!";
        Long time = Long.parseLong(time0);
        String end = MyUtils.getTimeFormat0(System.currentTimeMillis() - time);
        return end;
    }
}
