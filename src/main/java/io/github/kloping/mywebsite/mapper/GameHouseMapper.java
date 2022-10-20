package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.entitys.database.GameHouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author github.kloping
 */
@Mapper
public interface GameHouseMapper extends BaseMapper<GameHouse> {
    /**
     * qid
     *
     * @param qid
     * @return
     */
    @Select("SELECT * FROM `game_house` WHERE `qid`=${qid}")
    List<GameHouse> selectByQid(@Param("qid") Long qid);
}
