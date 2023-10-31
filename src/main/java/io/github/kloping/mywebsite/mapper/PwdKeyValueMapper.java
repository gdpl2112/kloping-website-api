package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.mapper.dao.PwdKeyValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author github.kloping
 */
@Mapper
public interface PwdKeyValueMapper extends BaseMapper<PwdKeyValue> {
    /**
     *
     * @param pwd
     * @return
     */
    @Select("select k from pkv where pwd=#{pwd};")
    List<String> selectKeys(@Param("pwd") String pwd);
}
