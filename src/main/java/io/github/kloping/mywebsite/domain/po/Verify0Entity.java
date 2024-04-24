package io.github.kloping.mywebsite.domain.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author github.kloping
 */
@Data
@Accessors(chain = true)
public class Verify0Entity {
    private String code;
    private Long expire;
}
