package io.github.kloping.mywebsite.entitys.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author github.kloping
 */
@Data
@EqualsAndHashCode
public class OrderReturn {
    public String orderId;
    public String qid;
    public String aid;
    public String money;
    public String src;
    public Long time = System.currentTimeMillis();
    public Long time0 = System.currentTimeMillis() + 600000;

    public OrderReturn summon() {
        src = "/imgs/alipay-" + money + ".jpg";
        return this;
    }
}
