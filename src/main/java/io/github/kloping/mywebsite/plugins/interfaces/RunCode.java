package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.PathValue;
import io.github.kloping.MySpringTool.annotations.http.HttpClient;
import io.github.kloping.MySpringTool.annotations.http.ParamName;
import io.github.kloping.MySpringTool.annotations.http.PostPath;
import io.github.kloping.MySpringTool.annotations.http.RequestBody;
import io.github.kloping.mywebsite.entitys.runcode.CodeEntity;
import io.github.kloping.mywebsite.entitys.runcode.CodeResponse;

/**
 * @author HRS-Computer
 */
@HttpClient("https://glot.io")
public interface RunCode {
    /**
     * run
     *
     * @param language
     * @param codeEntity
     * @param version
     * @return
     */
    @PostPath("run/")
    CodeResponse runAny(@PathValue String language,
                        @RequestBody(type = RequestBody.type.json) CodeEntity codeEntity,
                        @ParamName("version") String version);
}






