package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.entitys.database.GameGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author github.kloping
 */
@Mapper
public interface GameGoodsMapper extends BaseMapper<GameGoods> {
    /**
     * down all
     *
     * @return
     */
    @Update("UPDATE `game_goods` SET `up`=0,`num`=0")
    Integer updateAll();

    /**
     * select all
     * @return
     */
    @Select("SELECT * FROM `game_goods`")
    List<GameGoods> selectAll();

}
