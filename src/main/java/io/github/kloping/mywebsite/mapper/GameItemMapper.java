package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.entitys.database.GameItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author github.kloping
 */
@Mapper
public interface GameItemMapper extends BaseMapper<GameItem> {
    /**
     * qid
     *
     * @param qid
     * @return
     */
    @Select("SELECT * FROM `game_item` WHERE `owner_id`=${qid}")
    List<GameItem> selectByQid(@Param("qid") Long qid);

    /**
     * get one
     * @param qid
     * @param gid
     * @return
     */
    @Select("SELECT * FROM `game_item` WHERE `owner_id`=${qid} AND `goods_id`=${gid} ORDER BY `price0` LIMIT 1")
    GameItem selectOne(@Param("qid") Long qid, @Param("gid") Integer gid);
}
