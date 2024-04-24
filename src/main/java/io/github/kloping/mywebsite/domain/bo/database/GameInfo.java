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
public class GameInfo {
    @TableId
    private String name;
    private String value;

    public Integer valueAsInteger() {
        return Integer.valueOf(value);
    }

    public Long valueAsLong() {
        return Long.valueOf(value);
    }

    public Float valueAsFloat() {
        return Float.valueOf(value);
    }
}
