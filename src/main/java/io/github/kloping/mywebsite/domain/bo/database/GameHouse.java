package io.github.kloping.mywebsite.domain.bo.database;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author github.kloping
 */
@Getter
@Setter
@Accessors(chain = true)
public class GameHouse {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer cap;
    private Long money;
    /**
     * 拥有者
     */
    private Long qid = -1L;
}
