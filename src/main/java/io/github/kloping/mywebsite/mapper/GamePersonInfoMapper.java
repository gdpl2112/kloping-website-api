package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.entitys.database.GameInfo;
import io.github.kloping.mywebsite.entitys.database.GamePersonInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author github.kloping
 */
@Mapper
public interface GamePersonInfoMapper extends BaseMapper<GamePersonInfo> {
}
