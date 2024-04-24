package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;
import io.github.kloping.mywebsite.domain.bo.runcode.CodeEntity;
import io.github.kloping.mywebsite.domain.bo.runcode.CodeResponse;

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







