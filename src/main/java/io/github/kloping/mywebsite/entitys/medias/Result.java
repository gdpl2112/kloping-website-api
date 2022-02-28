package io.github.kloping.mywebsite.entitys.medias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result {
    private String[] data;
    private int num = -1;
    private String keyword;
    private long time;
    private int state = 0;
}
