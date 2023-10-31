package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.mapper.CommentMapper;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import io.github.kloping.mywebsite.mapper.dao.Comment;
import io.github.kloping.mywebsite.mapper.dao.UserTemp;
import org.springframework.beans.factory.annotation.Autowired;
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
    CommentMapper commentMapper;

    @GetMapping("getComment")
    public List<Comment> comments(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("nid") Integer nid) {
        if (nid == null) return null;
        List<Comment> comments = commentMapper.selectList(nid);
        if (userDetails != null) {
            for (Comment comment : comments) {
                if (comment.getNickName().equals(userDetails.getUsername())) {
                    comment.setC0(true);
                }
            }
        }
        return comments;
    }

    @GetMapping("/del_comment")
    public boolean delComment(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("id") Integer id) {
        if (id == null) return false;
        Comment comment = commentMapper.selectById(id);
        if (comment == null) return false;
        if (comment.getNickName().equals(userDetails.getUsername())) {
            return commentMapper.update(id) > 0;
        } else return false;
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
                .setIcon(userTemp.getIcon()).setC0(true);
        int r = commentMapper.insert(comment);
        return r > 0 ? comment : null;
    }
}
