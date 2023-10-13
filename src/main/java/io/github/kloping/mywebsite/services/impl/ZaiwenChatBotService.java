package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.services.ChatBotService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://www.zaiwen.top/">source</a>
 *
 * @author github.kloping
 */
@Service
public class ZaiwenChatBotService implements ChatBotService {
    public Connection reqConnection0 = null;

    {
        reqConnection0 = Jsoup.connect("https://ai.zaiwen.org.cn/message_poe")
                .header("Accept","*/*")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Access-Control-Allow-Origin","*")
                .header("Cache-Control","no-cache")
                .header("Connection","keep-alive")
                .header("Content-Type","application/json")
                .header("Host","www.zaiwen.org.cn")
                .header("Origin","https://www.zaiwen.top")
                .header("Pragma","no-cache")
                .header("Referer","https://www.zaiwen.top/")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67")
                .ignoreContentType(true).ignoreHttpErrors(true).method(Connection.Method.POST).timeout(120000);
    }

    @Override
    public String ai(String msg, String id) {
        JSONObject data = getRequestData(msg, id);
        try {
            String reqStr = data.toString();
            String out = reqConnection0.requestBody(reqStr).execute().body();
            arrayMap.get(id).add(new ChatNode("assistant", out));
            return out;
        } catch (IOException e) {
            return "ai error " + e.getMessage();
        }
    }

    @Override
    public String clear(String id) {
        return arrayMap.remove(id) == null ? "null" : "ok";
    }

    private JSONObject getRequestData(String msg, String id) {
        return getRequestData(msg, "chinchilla:0", id);
    }

    public Map<String, JSONArray> arrayMap = new HashMap<>();

    public JSONObject getRequestData(String msg, String mode, String id) {
        JSONObject data = new JSONObject();
        JSONArray arr = arrayMap.getOrDefault(id, new JSONArray());
        arr.add(new ChatNode("user", msg));
        data.put("message", arr);
        arrayMap.put(id, arr);
        data.put("mode", mode);
        data.put("key", null);
        return data;
    }

    public static final class ChatNode {
        private String role;
        private String content;

        public ChatNode(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this, true);
        }
    }
}
