package io.github.kloping.mywebsite.entitys;

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
    private Integer commentId;
    private Integer noticeId;
    private Long time;
    private String nickName;
    private String icon;
    private String content;
    private Integer state;
}
