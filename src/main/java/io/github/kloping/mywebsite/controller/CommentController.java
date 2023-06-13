package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.database.Comment;
import io.github.kloping.mywebsite.entitys.database.UserTemp;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import io.github.kloping.mywebsite.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    UserTempMapper userTempMapper;

    @PostMapping("pcm")
    public Comment pcm(
            @RequestParam("nid") Integer nid,
            @RequestParam("body") String body,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletRequest request) {
        UserTemp userTemp = userTempMapper.selectById(userDetails.getUsername());
        Comment comment = new Comment();
        comment.setState(0).setTime(System.currentTimeMillis()).setContent(body)
                .setNoticeId(nid).setNickName(userTemp.getNickname())
                .setIcon(userTemp.getIcon());
        return service.put(comment) > 0 ? comment : null;
    }

    @Value("${auth.url}")
    String url;

    @Value("${auth.pwd}")
    String pwd;
}
