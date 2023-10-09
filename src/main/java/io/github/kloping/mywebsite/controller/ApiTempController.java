package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * @author github.kloping
 */
@RestController
public class ApiTempController {
    @RequestMapping("/api/ai")
    public synchronized String ai(@RequestParam("req") String text) {
        try {
            StringBuilder sb = new StringBuilder("AI:");
            Document doc1 = Jsoup.connect("https://codenews.cc/chat1236").ignoreHttpErrors(true).ignoreContentType(true)
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                    .header("Cache-Control", "no-cache")
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("Cookie", "JSESSIONID=9e498748-833f-464e-8bba-8c7fddc67799; __51uvsct__Jv5WqdwaeNUAQcj2=1; __51vcke__Jv5WqdwaeNUAQcj2=555355c2-8f46-568a-ba9b-17896acedb40; __51vuft__Jv5WqdwaeNUAQcj2=1696820484414; Hm_lvt_402a012b4aa61ef41faebbe40f507bbc=1696820485; _ga=GA1.1.534881762.1696820485; __vtins__Jv5WqdwaeNUAQcj2=%7B%22sid%22%3A%20%22913dd4b7-e2d0-5337-95cc-8e070f97d31e%22%2C%20%22vd%22%3A%202%2C%20%22stt%22%3A%204190%2C%20%22dr%22%3A%204190%2C%20%22expires%22%3A%201696822288601%2C%20%22ct%22%3A%201696820488601%7D; _ga_K1P8CYBMKM=GS1.1.1696820484.1.1.1696820488.0.0.0; Hm_lpvt_402a012b4aa61ef41faebbe40f507bbc=1696820489")
                    .header("Origin", "https://codenews.cc")
                    .header("Pragma", "no-cache")
                    .header("Referer", "https://codenews.cc/chatgpt")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67")
                    .requestBody("chatgpt_input=" + URLEncoder.encode(text) + "&qa_type=programming&chatgpt_version_value=202301008").post();
            int s = 0;
            while (true) {
                Document doc0 = Jsoup.connect("https://codenews.cc/chat_stream").ignoreHttpErrors(true).ignoreContentType(true)
                        .header("Accept", "application/json, text/javascript, */*; q=0.01")
                        .header("Accept-Encoding", "gzip, deflate, br")
                        .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                        .header("Cache-Control", "no-cache")
                        .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                        .header("Cookie", "JSESSIONID=9e498748-833f-464e-8bba-8c7fddc67799; __51uvsct__Jv5WqdwaeNUAQcj2=1; __51vcke__Jv5WqdwaeNUAQcj2=555355c2-8f46-568a-ba9b-17896acedb40; __51vuft__Jv5WqdwaeNUAQcj2=1696820484414; Hm_lvt_402a012b4aa61ef41faebbe40f507bbc=1696820485; _ga=GA1.1.534881762.1696820485; __vtins__Jv5WqdwaeNUAQcj2=%7B%22sid%22%3A%20%22913dd4b7-e2d0-5337-95cc-8e070f97d31e%22%2C%20%22vd%22%3A%202%2C%20%22stt%22%3A%204190%2C%20%22dr%22%3A%204190%2C%20%22expires%22%3A%201696822288601%2C%20%22ct%22%3A%201696820488601%7D; _ga_K1P8CYBMKM=GS1.1.1696820484.1.1.1696820488.0.0.0; Hm_lpvt_402a012b4aa61ef41faebbe40f507bbc=1696820489")
                        .header("Origin", "https://codenews.cc")
                        .header("Pragma", "no-cache")
                        .header("Referer", "https://codenews.cc/chatgpt")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67")
                        .requestBody("current_req_count=1").post();
                String out = JSON.parseObject(doc0.body().text()).getString("data");
                if (out.isEmpty()) continue;
                if (out.endsWith("♜")) {
                    sb.append(out.substring(0, out.length() - 1));
                    break;
                } else sb.append(out);
                Thread.sleep(2000);
                s += 2;
                if (s >= 20) {
                    sb = new StringBuilder("请求超时,请重试!");
                    break;
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return "请求失败!";
        }
    }
}
