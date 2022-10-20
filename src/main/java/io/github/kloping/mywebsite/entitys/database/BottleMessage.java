package io.github.kloping.mywebsite.entitys.database;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author github.kloping
 */
@Data
@Accessors(chain = true)
public class BottleMessage {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("`gid`")
    private Long gid;

    @TableField("`sid`")
    private Long sid;

    @TableField("`time`")
    private Long time;

    @TableField("`name`")
    private String name;

    @TableField("`message`")
    private String message;

    @TableField("`state`")
    private Integer state;

}
