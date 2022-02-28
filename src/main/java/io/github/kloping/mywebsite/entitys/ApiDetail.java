package io.github.kloping.mywebsite.entitys;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiDetail extends ApiDetailM{
    public String name;
    private String desc;
    private String state;
    private String address;
    private String detail;
    private String simpleUrl;
}
