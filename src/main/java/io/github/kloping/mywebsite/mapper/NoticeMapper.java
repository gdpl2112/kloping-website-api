package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.entitys.database.Notice;
import org.apache.ibatis.annotations.Mapper;
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
}
