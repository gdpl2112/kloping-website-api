package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.FileWithPath;
import io.github.kloping.mywebsite.entitys.NoticePack;
import io.github.kloping.mywebsite.entitys.database.Notice;
import io.github.kloping.mywebsite.mapper.NoticeMapper;
import io.github.kloping.mywebsite.services.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;

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
    public String upload(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("file") @Nullable MultipartFile imageFile,
            @RequestParam("title") @Nullable String title,
            @RequestParam("code") String body
    ) {
        try {
            String img = "";
            if (imageFile != null && !imageFile.isEmpty()) {
                FileWithPath fwp = UtilsController.requestFile(false, "jpg");
                FileOutputStream fos = new FileOutputStream(fwp.getFile());
                fos.write(imageFile.getBytes());
                fos.close();
                img = "/" + fwp.getName();
            } else {
                img = "https://q1.qlogo.cn/g?b=qq&nk=" + userDetails.getUsername() + "&s=640";
            }
            title = title == null ? "未定义的标题" : title;
            body += "<br><hr>";
            return service.save(img, title, body, userDetails) ? "上传成功,管理员审核后上线" : "上传失败";
        } catch (Throwable e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("/modify")
    public String modify(@RequestBody String text) {
        try {
            JSONObject jo = JSON.parseObject(text);
            return service.modify(jo.getInteger("id"),
                    jo.getString("passwd"), jo.getString("code")) ? "ok" : "failure";
        } catch (Throwable e) {
            e.printStackTrace();
            return "err";
        }
    }

    @Autowired
    NoticeMapper mapper;

    @Value("${upload.passwd:123456}")
    String passwd;

    @GetMapping("/accept")
    public String accept(@RequestParam("id") Integer id, @RequestParam("pwd") String pwd) {
        if (!passwd.equals(pwd)) return "err";
        try {
            Notice notice = mapper.selectById(id);
            notice.setState(0);
            mapper.updateById(notice);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }
}
