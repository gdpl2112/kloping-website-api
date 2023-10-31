package io.github.kloping.mywebsite.mapper.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author github.kloping
 */
@Data
@Accessors(chain = true)
public class Illegal {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String content;
}
