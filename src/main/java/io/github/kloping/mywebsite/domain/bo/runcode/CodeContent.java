package io.github.kloping.mywebsite.domain.bo.runcode;

/**
 * @author HRS-Computer
 */
public class CodeContent {
    private String name;
    private String content;

    public String getName() {
        return this.name;
    }

    public CodeContent setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return this.content;
    }

    public CodeContent setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}