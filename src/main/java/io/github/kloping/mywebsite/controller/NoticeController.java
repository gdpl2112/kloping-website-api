package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.Notice;
import io.github.kloping.mywebsite.entitys.NoticePack;
import io.github.kloping.mywebsite.services.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}