package io.github.kloping.mywebsite.domain.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author github.kloping
 */
@Data
@Accessors(chain = true)
public class UserTemp {
    private String eid;
    private String pwd;
    @TableId
    private String nickname;
    private Long qid;
    private String icon;
    private String auth;
    private Long regt;
    private String type;
    private String annex;
}
