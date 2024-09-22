package io.github.kloping.mywebsite.controller.sys;

import io.github.kloping.common.Public;
import io.github.kloping.mywebsite.config.EmailConfig;
import io.github.kloping.mywebsite.domain.po.Comment;
import io.github.kloping.mywebsite.domain.po.Notice;
import io.github.kloping.mywebsite.domain.po.UserTemp;
import io.github.kloping.mywebsite.mapper.CommentMapper;
import io.github.kloping.mywebsite.mapper.NoticeMapper;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author github.kloping
 */
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserTempMapper userTempMapper;
    @Autowired
    NoticeMapper noticeMapper;
    @Resource
    EmailConfig emailConfig;

    @GetMapping("/get-comment")
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

    @GetMapping("/del-comment")
    public boolean delComment(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("id") Integer id) {
        if (id == null) return false;
        Comment comment = commentMapper.selectById(id);
        if (comment == null) return false;
        if (comment.getNickName().equals(userDetails.getUsername())) {
            return commentMapper.update(id) > 0;
        } else return false;
    }

    @PostMapping("/pcm")
    public Comment pcm(@RequestParam("nid") Integer nid, @RequestParam("body") String body, @AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request) {
        UserTemp userTemp = userTempMapper.selectById(userDetails.getUsername());
        Comment comment = new Comment();
        comment.setState(0).setTime(System.currentTimeMillis()).setContent(body)
                .setNoticeId(nid).setNickName(userTemp.getNickname())
                .setIcon(userTemp.getIcon()).setC0(true);
        int r = commentMapper.insert(comment);
        Public.EXECUTOR_SERVICE.submit(() -> {
            Notice notice = noticeMapper.selectById(nid);
            String eid = userTempMapper.selectById(notice.getAuthorName()).getEid();
            if (!notice.getAuthorName().equals(userTemp.getNickname())) {
                emailConfig.sendEmail(eid, "通知来自[若生er,WebSite]",
                        String.format("<h1>hi! %s</h1><p>%s在您发布的帖子<br>[%s]<br>发布了一条评论</p><br><p>%s</p>", notice.getAuthorName(), userTemp.getNickname(), notice.getTitle(), body));
            }
        });
        return r > 0 ? comment : null;
    }
}
