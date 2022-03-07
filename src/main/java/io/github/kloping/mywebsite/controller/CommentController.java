package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.Comment;
import io.github.kloping.mywebsite.services.ICommentService;
import io.github.kloping.url.UrlUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author github.kloping
 */
@RestController
public class CommentController {

    @Autowired
    ICommentService service;

    @GetMapping("getComment")
    public List<Comment> comments(@RequestParam("nid") Integer nid) {
        if (nid == null) return null;
        return service.get(nid);
    }

    @RequestMapping("pcm")
    public Comment pcm(
            @RequestParam("nid") Integer nid,
            @RequestParam("qid") @Nullable Long qid,
            @RequestParam("body") String body,
            @RequestParam("pwd") String pwd,
            HttpServletRequest request) {
        if (AUTH_MAP.containsKey(qid)) {
            if (AUTH_MAP.get(qid).trim().equalsIgnoreCase(pwd.trim())) {
                Comment comment = new Comment();
                comment.setState(0).setTime(System.currentTimeMillis()).setContent(body)
                        .setNoticeId(nid).setNickName(getNameByQid(qid)).setIcon("http://q.qlogo.cn/headimg_dl?dst_uin=" + qid + "&spec=40");
                return service.put(comment) > 0 ? comment : null;
            }
        }
        return null;
    }

    private String getNameByQid(Long qid) {
        try {
            String s = Jsoup.connect(url + "/getName?qid=" + qid).ignoreContentType(true).get().body().text();
            return s;
        } catch (IOException e) {
            e.printStackTrace();
            return "昵称";
        }
    }

    @Value("${auth.url}")
    String url;

    @Value("${auth.pwd}")
    String pwd;

    public static final Map<Long, String> AUTH_MAP = new HashMap<>();

    @RequestMapping("reqAuth")
    public Integer p0(@RequestParam("qid") Long qid) {
        try {
            String code = UrlUtils.getStringFromHttpUrl(url + "/authorization0?qid=" + qid + "&pwd=" + pwd);
            AUTH_MAP.put(qid, code);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
