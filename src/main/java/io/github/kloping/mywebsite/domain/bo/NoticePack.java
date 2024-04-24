package io.github.kloping.mywebsite.domain.bo;

import io.github.kloping.mywebsite.domain.po.Notice;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author github.kloping
 */
@Getter
@Setter
@Accessors(chain = true)
public class NoticePack {
    private Integer pn;
    private List<Notice> notices;
    private Integer max;
}
