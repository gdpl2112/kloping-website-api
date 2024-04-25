package io.github.kloping.mywebsite.domain.po;

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
public class Notice {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer state = 1;
    private Integer views;
    private String title;
    private String icon;
    private String date;
    private String html = "";
    private Long time;
    private String authorName;
    private Long authorId;
}
