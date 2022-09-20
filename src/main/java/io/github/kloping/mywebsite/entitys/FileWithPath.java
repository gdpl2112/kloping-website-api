package io.github.kloping.mywebsite.entitys;


import java.io.File;

/**
 * @author github.kloping
 */
public class FileWithPath {
    private File file;
    private String name;

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public FileWithPath(File file, String name) {
        this.file = file;
        this.name = name;
    }
}
