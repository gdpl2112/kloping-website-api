package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.entitys.database.GameEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author github.kloping
 */
@Mapper
public interface GameEventMapper extends BaseMapper<GameEvent> {
    /**
     * @return
     */
    @Select("SELECT * FROM `game_event`")
    List<GameEvent> selectAll();
}
