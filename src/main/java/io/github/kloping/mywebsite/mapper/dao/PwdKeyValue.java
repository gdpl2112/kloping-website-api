package io.github.kloping.mywebsite.mapper.dao;

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
    private String k;
    @TableField("`value`")
    private String value;
}
