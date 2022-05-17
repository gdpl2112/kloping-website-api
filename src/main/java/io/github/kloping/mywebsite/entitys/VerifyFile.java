package io.github.kloping.mywebsite.entitys;

import io.github.kloping.common.Public;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;

/**
 * @author github.kloping
 */
@Data
@Accessors(chain = true)
public abstract class VerifyFile {
    private Integer num;
    private String uuid;
    private File file;
    private String code;
    private Long time;

    public void start() {
        Public.EXECUTOR_SERVICE.submit(() -> {
            try {
                Thread.sleep(time);
                over();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * time stand
     */
    public abstract void over();
}
