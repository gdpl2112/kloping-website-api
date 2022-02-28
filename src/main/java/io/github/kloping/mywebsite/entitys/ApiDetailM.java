package io.github.kloping.mywebsite.entitys;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiDetailM {
    public String name;
    private String desc;
    private String state;
    private String address;
}
