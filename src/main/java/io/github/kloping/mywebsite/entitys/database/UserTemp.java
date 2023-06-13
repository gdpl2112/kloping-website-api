package io.github.kloping.mywebsite.entitys.database;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author github.kloping
 */
@Data
@Accessors(chain = true)
public class UserTemp {
    @TableId("eid")
    private String eid;
    private String pwd;
    private String nickname;
    private Long qid;
    private String icon;
    private String auth;
    private Long regt;
    private String type;
    private String annex;
}
