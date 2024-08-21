package io.github.kloping.mywebsite.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.controller.UtilsController;
import io.github.kloping.mywebsite.domain.bo.FileWithPath;
import io.github.kloping.mywebsite.domain.po.Favorites;
import io.github.kloping.mywebsite.domain.po.Notice;
import io.github.kloping.mywebsite.domain.po.UserTemp;
import io.github.kloping.mywebsite.mapper.FavoritesMapper;
import io.github.kloping.mywebsite.mapper.NoticeMapper;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import io.github.kloping.mywebsite.services.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @author github.kloping
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    INoticeService service;

    @GetMapping("/gets")
    public Notice[] gets() {
        return service.gets();
    }

    @GetMapping("/get-notice-id")
    public Object getOne(@RequestParam Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        return service.getOne(id);
    }

    @GetMapping("/deletable")
    public Boolean deletable(@RequestParam Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return false;
        Notice notice = mapper.selectById(id);
        return notice.getAuthorName().equals(userDetails.getUsername());
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return "Insufficient permissions";
        Notice notice = mapper.selectById(id);
        if (notice.getAuthorName().equals(userDetails.getUsername())) {
            notice.setState(1);
            mapper.updateById(notice);
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
            @RequestParam("code") String body) {
        if (userDetails == null) return "login state false";
//        body = uploadImg(body);
        try {
            String img = "";
            if (imageFile != null && !imageFile.isEmpty()) {
                FileWithPath fwp = UtilsController.requestFile(false, "jpg");
                FileOutputStream fos = new FileOutputStream(fwp.getFile());
                fos.write(imageFile.getBytes());
                fos.close();
                img = fwp.getName();
            } else {
                UserTemp userTemp = userTempMapper.selectById(userDetails.getUsername());
                img = userTemp.getIcon();
            }
            title = title == null ? "未定义的标题" : title;
            return service.save(img, title, body, userDetails) ? "上传成功,管理员审核后上线" : "上传失败";
        } catch (Throwable e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
//
//    public String uploadImg(String body) {
//        Document document = Jsoup.parse(body);
//        Elements es = document.getElementsByTag("img");
//        for (Element e : es) {
//            try {
//                String base64 = e.attr("src");
//                base64 = base64.substring(base64.indexOf("base64,") + 7);
//                byte[] bytes = Base64.getDecoder().decode(base64);
//                FileWithPath fwp = UtilsController.requestFile(false, "jpg");
//                FileOutputStream fos = new FileOutputStream(fwp.getFile());
//                fos.write(bytes);
//                fos.close();
//                e.attr("src", "/" + fwp.getName());
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//        body = document.body().html();
//        return body;
//    }

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
    FavoritesMapper favoritesMapper;

    @GetMapping("/favoritec")
    public boolean favoritec(@RequestParam("id") Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        QueryWrapper<Favorites> qw = new QueryWrapper<>();
        qw.eq("name", userDetails.getUsername());
        qw.eq("nid", id);
        if (favoritesMapper.selectCount(qw) > 0) {
            favoritesMapper.delete(qw);
            return false;
        } else {
            favoritesMapper.insert(new Favorites().setName(userDetails.getUsername()).setNid(id));
            return true;
        }
    }

    @GetMapping("/favorite")
    public boolean favorite(@RequestParam("id") Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        QueryWrapper<Favorites> qw = new QueryWrapper<>();
        qw.eq("name", userDetails.getUsername());
        qw.eq("nid", id);
        return favoritesMapper.selectCount(qw) > 0;
    }

    @GetMapping("/favorites")
    public Object favorites(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return null;
        List<JSONObject> jos = new LinkedList<>();
        for (Notice notice : mapper.selectTitleAndViewsByFavoriteName(userDetails.getUsername())) {
            JSONObject jo = new JSONObject();
            jo.put("title", notice.getTitle());
            jo.put("views", notice.getViews());
            jo.put("id", notice.getId());
            jos.add(jo);
        }
        return jos;
    }

    @GetMapping("/myall")
    public Object myAll(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return null;
        List<JSONObject> jos = new LinkedList<>();
        QueryWrapper<Notice> qw0 = new QueryWrapper<>();
        qw0.eq("author_name", userDetails.getUsername());
        qw0.eq("state", 0);
        for (Notice notice : mapper.selectList(qw0)) {
            JSONObject jo = new JSONObject();
            jo.put("title", notice.getTitle());
            jo.put("views", notice.getViews());
            jo.put("id", notice.getId());
            jos.add(jo);
        }
        return jos;
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
