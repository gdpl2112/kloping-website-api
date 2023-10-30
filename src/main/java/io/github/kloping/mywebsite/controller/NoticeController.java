package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.FileWithPath;
import io.github.kloping.mywebsite.entitys.NoticePack;
import io.github.kloping.mywebsite.entitys.database.Notice;
import io.github.kloping.mywebsite.entitys.database.UserTemp;
import io.github.kloping.mywebsite.mapper.NoticeMapper;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import io.github.kloping.mywebsite.services.INoticeService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import static io.github.kloping.mywebsite.services.impl.NoticeServiceImpl.notices;
import static io.github.kloping.mywebsite.services.impl.NoticeServiceImpl.notices2;

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
    public Object get1(@RequestParam Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.get0(id);
    }

    @GetMapping("deletable")
    public Boolean deletable(@RequestParam Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return false;
        Notice notice = mapper.selectById(id);
        return notice.getAuthorName().equals(userDetails.getUsername());
    }

    @GetMapping("deleten")
    public String deleten(@RequestParam Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return "Insufficient permissions";
        Notice notice = mapper.selectById(id);
        if (notice.getAuthorName().equals(userDetails.getUsername())) {
            notice.setState(1);
            mapper.updateById(notice);
            notices.clear();
            notices2.clear();
            return "OK";
        } else return "Insufficient permissions!";
    }

    @Autowired
    UserTempMapper userTempMapper;

    @PostMapping("/upload")
    public String upload(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("file") @Nullable MultipartFile imageFile,
            @RequestParam("title") @Nullable String title,
            @RequestParam("code") String body
    ) {
        if (userDetails == null) return "login state false";
        body = uploadImg(body);
        try {
            String img = "";
            if (imageFile != null && !imageFile.isEmpty()) {
                FileWithPath fwp = UtilsController.requestFile(false, "jpg");
                FileOutputStream fos = new FileOutputStream(fwp.getFile());
                fos.write(imageFile.getBytes());
                fos.close();
                img = "/" + fwp.getName();
            } else {
                UserTemp userTemp = userTempMapper.selectById(userDetails.getUsername());
                img = userTemp.getIcon();
            }
            title = title == null ? "未定义的标题" : title;
            body += "<br><hr>";
            return service.save(img, title, body, userDetails) ? "上传成功,管理员审核后上线" : "上传失败";
        } catch (Throwable e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String uploadImg(String body) {
        Document document = Jsoup.parse(body);
        Elements es = document.getElementsByTag("img");
        for (Element e : es) {
            try {
                String base64 = e.attr("src");
                base64 = base64.substring(base64.indexOf("base64,") + 7);
                byte[] bytes = Base64.getDecoder().decode(base64);
                FileWithPath fwp = UtilsController.requestFile(false, "jpg");
                FileOutputStream fos = new FileOutputStream(fwp.getFile());
                fos.write(bytes);
                fos.close();
                e.attr("src", "/" + fwp.getName());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        body = document.body().html();
        return body;
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
            notices.clear();
            notices2.clear();
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "err";
        }
    }
}
