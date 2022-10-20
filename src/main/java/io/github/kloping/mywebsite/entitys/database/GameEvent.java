package io.github.kloping.mywebsite.entitys.database;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class GameEvent {
    @TableId
    private Integer id;
    @TableField("`desc`")
    private String desc;
    private Integer goodsId;
    private Integer maxOffset;
    private Integer minOffset;
}
