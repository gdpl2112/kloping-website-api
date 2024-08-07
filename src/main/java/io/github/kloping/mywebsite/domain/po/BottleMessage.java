package io.github.kloping.mywebsite.domain.po;

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
    private String gid;

    @TableField("`sid`")
    private String sid;

    @TableField("`time`")
    private Long time;

    @TableField("`name`")
    private String name;

    @TableField("`message`")
    private String message;

    @TableField("`state`")
    private Integer state;
}
