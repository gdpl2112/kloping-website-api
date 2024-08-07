package io.github.kloping.mywebsite.plugins.detail;

import io.github.kloping.MySpringTool.annotations.AutoStand;
import io.github.kloping.MySpringTool.annotations.Entity;
import io.github.kloping.mywebsite.domain.bo.runcode.CodeEntity;
import io.github.kloping.mywebsite.domain.bo.runcode.CodeResponse;
import io.github.kloping.mywebsite.plugins.interfaces.RunCode;

import java.io.IOException;


/**
 * @author github-kloping
 */
@Entity
public class RunAll {
    @AutoStand
    RunCode runCode;

    public CodeResponse runJava(CodeEntity entity) throws IOException {
        return runCode.runAny("java", entity, "latest");
    }

    public CodeResponse runC(CodeEntity entity) throws IOException {
        return runCode.runAny("c", entity, "latest");
    }

    public CodeResponse runPython(CodeEntity entity) throws IOException {
        return runCode.runAny("python", entity, "latest");
    }

    public CodeResponse runAny(CodeEntity entity, String language) throws IOException {
        return runAny(entity, language, "latest");
    }

    public CodeResponse runAny(CodeEntity entity, String language, String v) throws IOException {
        return runCode.runAny(language, entity, v);
    }
}
