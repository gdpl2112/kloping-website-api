package io.github.kloping.mywebsite.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
