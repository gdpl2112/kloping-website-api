package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.medias.Song;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.services.ChatBotService;
import io.github.kloping.mywebsite.services.impl.SearchSongKugou;
import io.github.kloping.url.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    SearchSongKugou searchSongKugou;


    private List<JSONObject> tempList = new ArrayList<>();

    private int index0 = 1;
    private int indexMax = 20;

    @RequestMapping("/get-music")
    public Object getMusic() {
        try {
            if (tempList.isEmpty() || index0++ % indexMax == 0) {
                String data = UrlUtils.getStringFromHttpUrl("http://localhost/get?pwd=r&key=songs");
                for (Object o : JSONArray.parseArray(data)) {
                    String name = o.toString();
                    Songs songs = searchSongKugou.searchSong(name, 1);
                    Song song = songs.getData()[0];
                    JSONObject jo = new JSONObject();
                    jo.put("name", song.getMedia_name());
                    jo.put("artist", song.getAuthor_name());
                    jo.put("cover", song.getImgUrl());
                    jo.put("url", song.getSongUrl());
                    tempList.add(jo);
                }
            }
            return tempList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/clear-music")
    public void clearMusic() {
        tempList.clear();
    }
}
