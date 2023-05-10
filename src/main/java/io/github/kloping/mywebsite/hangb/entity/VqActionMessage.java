package io.github.kloping.mywebsite.hangb.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author github.kloping
 */
@TableName("vqam")
public class VqActionMessage {
    private Long qid;
    /**
     * 1 时间
     * 2 触发
     */
    private Integer type;
    private Integer scope;
    private String wesks;
    private Long target;
    private String trigger;
    private String action;
}
