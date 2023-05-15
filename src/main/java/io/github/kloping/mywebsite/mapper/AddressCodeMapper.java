package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.entitys.AddressCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author github.kloping
 */
@Mapper
public interface AddressCodeMapper extends BaseMapper<AddressCode> {
    @Select("SELECT * FROM address_code WHERE c_name LIKE '#{name}%';")
    List<AddressCode> selectLike(@Param("name") String name);
}
