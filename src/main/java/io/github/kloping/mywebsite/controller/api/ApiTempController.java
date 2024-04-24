package io.github.kloping.mywebsite.controller.api;

import io.github.kloping.file.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author github.kloping
 */
@RestController
public class ApiTempController {


    @RequestMapping("/test")
    public String test(HttpServletRequest request) throws IOException {
        return "e";
    }

    @RequestMapping("/get-music")
    public Object getMusic(HttpServletRequest request) {
        return FileUtils.getStringFromFile("./files/songs.json");
    }
}
