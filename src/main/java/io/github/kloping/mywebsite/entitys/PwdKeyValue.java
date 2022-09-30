package io.github.kloping.mywebsite.entitys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author github.kloping
 */
@Data
@Accessors(chain = true)
@TableName("pkv")
public class PwdKeyValue {
    @TableField("`pwd`")
    private String pwd;
    @TableField("`k`")
    private String key;
    @TableField("`value`")
    private String value;
}
