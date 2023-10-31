package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.mapper.dao.Verify0Entity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author github.kloping
 */
@Mapper
public interface Verify0Mapper extends BaseMapper<Verify0Entity> {
    /**
     * get one
     *
     * @param code
     * @return
     */
    @Select("select * from verify0_entity where code=#{code}")
    Verify0Entity selectByCode(@Param("code") String code);
}
