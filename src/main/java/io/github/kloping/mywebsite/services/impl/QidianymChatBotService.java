package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.services.ChatBotService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://ai.qidianym.net">source</a>
 *
 * @author github.kloping
 */
@Service
public class QidianymChatBotService implements ChatBotService {
    public Connection reqConnection0 = null;

    {
        reqConnection0 = Jsoup.connect("https://ai.qidianym.net/api/chat-process")
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Cache-Control", "no-cache")
                .header("Content-Type", "application/json")
                .header("Origin", "https://ai.qidianym.net")
                .header("Pragma", "no-cache")
                .header("Referer", "https://ai.qidianym.net/?from=yoogle.top")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67").ignoreContentType(true).ignoreHttpErrors(true).method(Connection.Method.POST).timeout(120000);
    }

    public JSONObject reqData = null;

    {
        reqData = JSON.parseObject("{\"prompt\":\"\",\"options\":{\"parentMessageId\":\"\"}," +
                "\"systemMessage\":\"You are the Chat BOT artificial intelligence assistant.Don't answer any illegal questions about politics, pornography, violence, etc., nor give any reasons.When answering questions,Respond using markdown. please try to provide detailed answers in Chinese to ensure understanding and accuracy.Knowledge deadline: March 1st, 2023  nCurrent date: 2023-10-09.Please answer this question according to the above rules\"" +
                ",\"temperature\":0.8,\"top_p\":1}");
    }


    private Map<String, String> id2pmid = new HashMap<>();

    @Override
    public String ai(String text, String id) {
        try {
            reqData.getJSONObject("options").put("parentMessageId", id2pmid.getOrDefault(id, ""));
            reqData.put("prompt", text);
            BufferedReader reader = new BufferedReader(new InputStreamReader(reqConnection0.requestBody(reqData.toJSONString()).execute().bodyStream()));
            String out = null;
            while (true) {
                String line = reader.readLine();
                if (line != null) out = line;
                else break;
            }
            JSONObject jout = JSON.parseObject(out);
            id2pmid.put(id, jout.getJSONObject("detail").getString("id"));
            return jout.getString("text");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String clear(String id) {
        return id2pmid.remove(id);
    }
}
