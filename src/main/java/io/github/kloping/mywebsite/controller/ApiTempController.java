package io.github.kloping.mywebsite.controller;

import io.github.kloping.file.FileUtils;
import io.github.kloping.mywebsite.services.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author github.kloping
 */
@RestController
public class ApiTempController {
    @Autowired
    @Qualifier("zaiwenChatBotService")
    ChatBotService service0;

    @Autowired
    @Qualifier("qidianymChatBotService")
    ChatBotService service1;

    @RequestMapping("/api/ai")
    public String ai(@RequestParam("req") String text, @RequestParam("id") String id) {
        return service0.ai(text, id);
    }

    @RequestMapping("/api/ai/clear")
    public String clearAi(@RequestParam("id") String id) {
        return service0.clear(id);
    }

    @RequestMapping("/api/ai1")
    public String ai1(@RequestParam("req") String text, @RequestParam("id") String id) {
        return service1.ai(text, id);
    }

    @RequestMapping("/api/ai1/clear")
    public String clearAi1(@RequestParam("id") String id) {
        return service1.clear(id);
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request) {
        return UtilsController.getHost(request);
    }

    @RequestMapping("/get-music")
    public Object getMusic(HttpServletRequest request) {
        return FileUtils.getStringFromFile("./files/songs.json");
    }
}
