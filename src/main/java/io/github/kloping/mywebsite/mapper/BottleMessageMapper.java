package io.github.kloping.mywebsite.mapper;

/**
 * @author github.kloping
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.domain.po.BottleMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BottleMessageMapper extends BaseMapper<BottleMessage> {
}
