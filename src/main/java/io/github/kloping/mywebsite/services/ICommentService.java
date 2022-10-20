package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.entitys.database.Comment;

import java.util.List;

/**
 * @author github.kloping
 */
public interface ICommentService {
    /**
     * get comment by nid
     * @param noticeId
     * @return
     */
    List<Comment> get(Integer noticeId);

    /**
     * insert
     * @param comment
     * @return
     */
    Integer put(Comment comment);
}
