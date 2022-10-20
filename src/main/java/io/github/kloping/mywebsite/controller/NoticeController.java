package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.database.Notice;
import io.github.kloping.mywebsite.entitys.NoticePack;
import io.github.kloping.mywebsite.services.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

/**
 * @author github.kloping
 */
@RestController
public class NoticeController {
    @Autowired
    INoticeService service;

    @GetMapping("/getNotice")
    public NoticePack get0(@RequestParam @Nullable Integer pn) {
        if (pn == null) pn = 1;
        return service.get(pn);
    }

    @GetMapping("/getNotice0")
    public NoticePack get2(@RequestParam @Nullable Integer pn) {
        if (pn == null) pn = 1;
        return service.get1(pn);
    }

    @GetMapping("/getNoticeById")
    public Notice get1(@RequestParam Integer id) {
        return service.get0(id);
    }

    @PostMapping("/upload")
    public String upload(@RequestBody String body) {
        try {
            return service.save(body) ? "ok" : "failure";
        } catch (Throwable e) {
            e.printStackTrace();
            return "err";
        }
    }

    @PostMapping("/modify")
    public String modify(@RequestBody String text) {
        try {
            JSONObject jo = JSON.parseObject(text);
            return service.modify(jo.getInteger("id"), jo.getString("passwd"), jo.getString("code")) ? "ok" : "failure";
        } catch (Throwable e) {
            e.printStackTrace();
            return "err";
        }
    }
}
