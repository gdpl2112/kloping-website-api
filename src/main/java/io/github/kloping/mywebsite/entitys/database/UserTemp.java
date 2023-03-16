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
    @TableId("qid")
    private Long qid;
    private Integer code;
}
