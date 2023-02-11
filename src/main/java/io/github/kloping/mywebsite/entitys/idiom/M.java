package io.github.kloping.mywebsite.entitys.idiom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 *
 * @author github-kloping
 * @date 2023-02-11
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class M {
    private int state;
    private String word;
    private String[] pinyin;
}
