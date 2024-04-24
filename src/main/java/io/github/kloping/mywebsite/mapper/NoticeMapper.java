package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.domain.po.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author github.kloping
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    /**
     * 1
     *
     * @return
     */
    @Select("SELECT id,state,views,title,icon,date,author_name,author_id,time FROM `notice` WHERE `state`=0  order by `time` desc;")
    List<Notice> ln();
    /**
     * 1
     *
     * @return
     */
    @Select("SELECT * FROM `notice` WHERE state=1 order by `id` desc LIMIT 1;")
    Notice getUtmost();

    /**
     * @param name
     * @return
     */
    @Select("select title,views,id from notice left join favorites on favorites.nid=notice.id where name=#{name} and state=0;")
    List<Notice> selectTitleAndViewsByFavoriteName(@Param("name") String name);
}
