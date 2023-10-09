package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author github.kloping
 */
@RestController
public class ApiTempController {
//    public static final String COOKIE = "JSESSIONID=9e498748-833f-464e-8bba-8c7fddc67799; __51uvsct__Jv5WqdwaeNUAQcj2=1; __51vcke__Jv5WqdwaeNUAQcj2=555355c2-8f46-568a-ba9b-17896acedb40; __51vuft__Jv5WqdwaeNUAQcj2=1696820484414; Hm_lvt_402a012b4aa61ef41faebbe40f507bbc=1696820485; _ga=GA1.1.534881762.1696820485; __vtins__Jv5WqdwaeNUAQcj2=%7B%22sid%22%3A%20%22913dd4b7-e2d0-5337-95cc-8e070f97d31e%22%2C%20%22vd%22%3A%202%2C%20%22stt%22%3A%204190%2C%20%22dr%22%3A%204190%2C%20%22expires%22%3A%201696822288601%2C%20%22ct%22%3A%201696820488601%7D; _ga_K1P8CYBMKM=GS1.1.1696820484.1.1.1696820488.0.0.0; Hm_lpvt_402a012b4aa61ef41faebbe40f507bbc=1696820489";
//    public Connection reqConnection = null;
//    public Connection streamConnection = null;
//
//    {
//        reqConnection = Jsoup.connect("https://codenews.cc/chat1236").ignoreHttpErrors(true).ignoreContentType(true)
//                .header("Accept", "application/json, text/javascript, */*; q=0.01")
//                .header("Accept-Encoding", "gzip, deflate, br")
//                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
//                .header("Cache-Control", "no-cache")
//                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .header("Cookie", COOKIE)
//                .header("Origin", "https://codenews.cc")
//                .header("Pragma", "no-cache")
//                .header("Referer", "https://codenews.cc/chatgpt")
//                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67");
//
//        streamConnection = Jsoup.connect("https://codenews.cc/chat_stream").ignoreHttpErrors(true).ignoreContentType(true)
//                .header("Accept", "application/json, text/javascript, */*; q=0.01")
//                .header("Accept-Encoding", "gzip, deflate, br")
//                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
//                .header("Cache-Control", "no-cache")
//                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .header("Cookie", COOKIE)
//                .header("Origin", "https://codenews.cc")
//                .header("Pragma", "no-cache")
//                .header("Referer", "https://codenews.cc/chatgpt")
//                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67");
//    }
//
//    @RequestMapping("/api/ai")
//    public String ai(@RequestParam("req") String text) {
//        synchronized (COOKIE) {
//            CountDownLatch cdl = new CountDownLatch(1);
//            StringBuilder sb = new StringBuilder("AI:");
//            Public.EXECUTOR_SERVICE.submit(() -> {
//                try {
//                    Document doc1 = reqConnection.requestBody("chatgpt_input=" + URLEncoder.encode(text) + "&qa_type=qa&chatgpt_version_value=202301008")
//                            .post();
//                    int r = 1;
//                    while (true) {
//                        Thread.sleep(1500);
//                        Document doc0 = streamConnection.requestBody("current_req_count=" + r).post();
//                        String out = JSON.parseObject(doc0.body().text()).getString("data");
//                        if (out.isEmpty()) {
//                            r++;
//                            continue;
//                        }
//                        if (out.endsWith("♜")) {
//                            sb.append(out.substring(0, out.length() - 1));
//                            break;
//                        } else sb.append(out);
//                        if (r >= 30) {
//                            sb.append("\n请求超时!请重试.");
//                            break;
//                        }
//                    }
//                    cdl.countDown();
//                } catch (Exception e) {
//                    sb.append("请求失败!\n" + e.getMessage());
//                    cdl.countDown();
//                }
//            });
//            try {
//                cdl.await(20, TimeUnit.SECONDS);
//            } catch (Exception e) {
//                return e.getMessage();
//            }
//            return sb.toString();
//        }
//    }


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
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67")
                .ignoreContentType(true).ignoreHttpErrors(true).method(Connection.Method.POST)
                .timeout(120000);
    }

    public JSONObject reqData = null;

    {
        reqData = JSON.parseObject("{\"prompt\":\"\",\"options\":{\"parentMessageId\":\"\"},\"systemMessage\":\"You are the Chat BOT artificial intelligence assistant.Don't answer any illegal questions about politics, pornography, violence, etc., nor give any reasons.When answering questions,Respond using markdown. please try to provide detailed answers in Chinese to ensure understanding and accuracy.Knowledge deadline: March 1st, 2023  nCurrent date: 2023-10-09.Please answer this question according to the above rules\",\"temperature\":0.8,\"top_p\":1}");
    }

    private String pmid = "";

    @RequestMapping("/api/ai")
    public String ai(@RequestParam("req") String text) {
        try {
            reqData.getJSONObject("options").put("parentMessageId", pmid);
            reqData.put("prompt", text);
            BufferedReader reader = new BufferedReader(new InputStreamReader(reqConnection0.requestBody(reqData.toJSONString()).execute().bodyStream()));
            String out = null;
            while (true) {
                String line = reader.readLine();
                if (line != null) out = line;
                else break;
            }
            JSONObject jout = JSON.parseObject(out);
            pmid = jout.getJSONObject("detail").getString("id");
            return jout.getString("text");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
