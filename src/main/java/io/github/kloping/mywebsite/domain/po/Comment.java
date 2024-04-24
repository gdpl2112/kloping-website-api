package io.github.kloping.mywebsite.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer commentId;
    private Integer noticeId;
    private Long time;
    private String nickName;
    private String icon;
    private String content;
    private Integer state;

    @TableField(exist = false)
    private Boolean c0 = false;
}
