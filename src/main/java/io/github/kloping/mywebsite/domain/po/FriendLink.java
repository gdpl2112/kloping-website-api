package io.github.kloping.mywebsite.domain.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author github.kloping
 */
@Data
@Accessors(chain = true)
@TableName("friend_link")
public class FriendLink {
    @TableId
    private String name;
    private String url;
    private String icon;
    @TableField("`desc`")
    private String desc;
    private String color;
}
