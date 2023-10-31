package io.github.kloping.mywebsite.mapper.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author github.kloping
 */
@Data
@TableName("bg_imgs")
public class BgImg {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("url")
    private String url;
    @TableField("type")
    private Integer type;
    private String eid;

    @TableField(exist = false)
    private String u0;
}
