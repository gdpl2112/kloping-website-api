package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.mapper.dao.BgImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author github.kloping
 */
@Mapper
public interface BgImgMapper extends BaseMapper<BgImg> {
    @Select("select * from bg_imgs where eid=#{id} and (`type`=1 or `type`=0)")
    List<BgImg> selectListByEid(@Param("id") String id);

    @Update("update bg_imgs set `type`=3 where eid=#{name} and url=#{url}")
    int deleteByEidAndUrl(@Param("name") String name, @Param("url") String url);
}
