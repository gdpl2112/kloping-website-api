package io.github.kloping.mywebsite.entitys.database;

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
public class GameItem {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer goodsId;
    private Integer num;
    private Integer price0;
    private Long ownerId;
}
