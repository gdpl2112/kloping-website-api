package io.github.kloping.mywebsite.entitys;

import lombok.Data;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * @author HRS-Computer
 */
@Data
@Accessors(chain = true)
public class ApiDetail extends ApiDetailM implements Comparable<ApiDetailM> {
    @Override
    public int compareTo(@NotNull ApiDetailM o) {
        return o.getName().compareTo(this.getName());
    }

    public String name;
    private String desc;
    private String state;
    private String address;
    private String detail;
    private String simpleUrl;
}
