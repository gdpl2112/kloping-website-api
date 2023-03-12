package io.github.kloping.mywebsite.entitys;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author HRS-Computer
 */
@Data
@Accessors(chain = true)
public class PayOut {
    private Integer code;
    private String text;
    private Long uin;
    private Long payuin;
    private String payid;
    private Long time;
}
