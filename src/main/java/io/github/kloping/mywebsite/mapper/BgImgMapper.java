package io.github.kloping.mywebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.kloping.mywebsite.entitys.database.BgImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author github.kloping
 */
@Mapper
public interface BgImgMapper extends BaseMapper<BgImg> {

    /**
     * select one rand by type
     *
     * @param type
     * @return
     */
    @Select("SELECT t1.id,t1.url,t1.type FROM bg_imgs AS t1 JOIN (SELECT ROUND(RAND()*(SELECT MAX(id) \n" +
            "FROM bg_imgs where type=#{type})) AS id) AS t2 WHERE t1.id>=t2.id ORDER BY t1.id LIMIT 1;")
    BgImg selectOneRand(@Param("type") Integer type);
}
