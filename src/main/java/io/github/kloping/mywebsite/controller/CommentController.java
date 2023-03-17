package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.database.Comment;
import io.github.kloping.mywebsite.services.ICommentService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

    @PostMapping("pcm")
    public Comment pcm(
            @RequestParam("nid") Integer nid,
            @RequestParam("body") String body,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletRequest request) {
        Long qid = Long.valueOf(userDetails.getUsername());
        Comment comment = new Comment();
        comment.setState(0).setTime(System.currentTimeMillis()).setContent(body)
                .setNoticeId(nid).setNickName(getNameByQid(qid)).setIcon("http://q.qlogo.cn/headimg_dl?dst_uin=" + qid + "&spec=40");
        return service.put(comment) > 0 ? comment : null;
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
}
