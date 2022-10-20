package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.entitys.database.PwdKeyValue;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author github.kloping
 */
@Mapper
public interface PwdKeyValueMapper extends BaseMapper<PwdKeyValue> {
}
