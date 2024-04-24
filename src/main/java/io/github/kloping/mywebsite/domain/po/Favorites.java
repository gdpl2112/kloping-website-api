package io.github.kloping.mywebsite.domain.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author github.kloping
 */
@Data
@Accessors(chain = true)
public class Favorites {
    private String name;
    private Integer nid;
}
