package io.github.kloping.mywebsite.entitys.database;

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
public class GameGoods {
    @TableId
    private Integer id;
    private String name;
    private Integer price0;
    private Integer price1;
    private Integer num;
    private String src;
    private Boolean up = false;
}
