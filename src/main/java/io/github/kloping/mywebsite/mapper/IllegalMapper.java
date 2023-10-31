package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.mapper.dao.Illegal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author github.kloping
 */
@Mapper
public interface IllegalMapper extends BaseMapper<Illegal> {

    /**
     * all
     *
     * @return
     */
    @Select("SELECT * FROM `illegal`")
    List<Illegal> selectAll();
}
