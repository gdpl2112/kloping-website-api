package io.github.kloping.mywebsite.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.mapper.dao.Comment;
import io.github.kloping.mywebsite.mapper.CommentMapper;
import io.github.kloping.mywebsite.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author github.kloping
 */
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public Integer put(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public List<Comment> get(Integer noticeId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("notice_id", noticeId);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return comments;
    }
}
