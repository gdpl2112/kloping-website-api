package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.entitys.database.GameBank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author github.kloping
 */
@Mapper
public interface GameBankMapper extends BaseMapper<GameBank> {
    /**
     * 1
     * @return
     */
    @Select("SELECT * FROM `game_bank`")
    List<GameBank> selectAll();
}
