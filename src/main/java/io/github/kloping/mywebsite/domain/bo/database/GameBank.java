package io.github.kloping.mywebsite.domain.bo.database;

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
public class GameBank {
    @TableId
    private Long pid;
    private Long money;
}
