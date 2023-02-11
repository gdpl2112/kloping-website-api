package io.github.kloping.mywebsite.entitys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * @author HRS-Computer
 */
@Data
@Accessors(chain = true)
public class ApiDetail implements Comparable<ApiDetail> {
    @Override
    public int compareTo(@NotNull ApiDetail o) {
        return o.getName().compareTo(this.getName());
    }

    @TableId(type = IdType.AUTO)
    public Integer id = null;
    public String name;
    @TableField("`desc`")
    private String desc;
    private String state;
    private String address;
    private String detail;
    private String simpleUrl;
}
