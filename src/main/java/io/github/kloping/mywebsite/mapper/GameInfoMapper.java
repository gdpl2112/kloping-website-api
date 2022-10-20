package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.entitys.database.GameInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author github.kloping
 */
@Mapper
public interface GameInfoMapper extends BaseMapper<GameInfo> {
}
