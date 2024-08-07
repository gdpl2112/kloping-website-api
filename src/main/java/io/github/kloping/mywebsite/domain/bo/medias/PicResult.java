package io.github.kloping.mywebsite.domain.bo.medias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PicResult {
    private String[] data;
    private int num = -1;
    private String keyword;
    private long time;
    private int state = 0;
}
