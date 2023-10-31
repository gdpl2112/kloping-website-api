package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.mapper.dao.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author github.kloping
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("SELECT * FROM comment WHERE notice_id=#{nid} AND state=0")
    List<Comment> selectList(@Param("nid") Integer id);

    @Update("UPDATE comment SET state=1 WHERE comment_id=#{id}")
    int update(@Param("id") Integer id);

}
