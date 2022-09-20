package io.github.kloping.mywebsite.controller;

import io.github.kloping.date.DateUtils;
import io.github.kloping.file.FileUtils;
import io.github.kloping.mywebsite.entitys.FileWithPath;
import io.github.kloping.mywebsite.entitys.OnlyData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * @author github-kloping
 */
@RestController
public class UtilsController {
    /**
     * 获取重定向地址
     */
    public static String getRedirectUrl(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(5000);
        return conn.getHeaderField("Location");
    }


    @GetMapping("/getMCloud3")
    public String getMCloud3Url() throws Exception {
        Connection connection = Jsoup.connect("http://fy4.nsmc.org.cn/nsmc/cn/image/area.html")
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.30");
        Document document = connection.get();
        Element element = document.getElementById("j-video");
        String url = element.attr("src");
        return url;
    }

    @RequestMapping("/getCloudPics")
    public List<String> getCloudPic() {
        try {
            String baseUrl = "http://www.nsmc.org.cn/NSMC/datalist/fy2_color.txt";
            String mn = "http://img.nsmc.org.cn/CLOUDIMAGE/FY2/WXCL/%s";
            byte[] bytes = io.github.kloping.url.UrlUtils.getBytesFromHttpUrl(baseUrl);
            String[] pics = new String(bytes, "utf-8").trim().split(",");
            System.out.println(Arrays.toString(pics).replaceAll(",", "\n"));
            for (int i = 0; i < pics.length; i++)
                pics[i] = String.format(mn, pics[i].trim()).trim();
            return Arrays.asList(pics);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static interface Notice {
        /**
         * on notice
         *
         * @param packName
         * @param title
         * @param text
         */
        void notice(String packName, String title, String text);
    }

    public static final List<Notice> NOTICES = new ArrayList<>();

    @GetMapping("/ok")
    public String ok(String a) {
        return "ok";
    }

    @Value("${auth.pwd}")
    String pwd;

    public Map<String, String> dataMap = new HashMap<>();

    @GetMapping("/put")
    public String put(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("pwd") String pwd) {
        if (this.pwd.equals(pwd)) {
            return dataMap.put(key, value);
        }
        return "wrong password";
    }

    @GetMapping("/get")
    public String get(@RequestParam("key") String key, @RequestParam("pwd") String pwd) {
        if (this.pwd.equals(pwd)) {
            return dataMap.get(key);
        }
        return "wrong password";
    }

    @GetMapping("/notice")
    public String notice(
            @RequestParam String packName,
            @RequestParam String title,
            @RequestParam String text) {
        for (Notice notice : NOTICES) {
            notice.notice(packName, title, text);
        }
        return "success";
    }

    @PostMapping("/uploadImg")
    public String upload(@RequestParam("key") String key, @RequestBody OnlyData data, HttpServletRequest request) {
        if (!this.pwd.equals(pwd)) return "wrong password";
        byte[] bytes = Base64.getDecoder().decode(data.getData().toString());
        String name = save(bytes, false);
        return name;
    }

    @GetMapping("/transImg")
    public void proxy(@RequestParam("url") String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            Connection connection = null;
            connection = org.jsoup.Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true)
                    .header("Host", new URL(url).getHost())
                    .header("accept-encoding", "gzip, deflate, br")
                    .userAgent(
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36 Edg/100.0.1185.50"
                    ).method(Connection.Method.GET);
            Connection.Response resp = connection.execute();
            byte[] bytes = resp.bodyAsBytes();
            String name = save(bytes, true);
            response.sendRedirect("http://kloping.top/" + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getHost")
    public String getHost(@RequestParam("url") String url) {
        String host = "localhost";
        try {
            URL u = new URL(url);
            host = u.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return host;
    }

    public static FileWithPath requestFile(boolean isTemp) {
        return requestFile(isTemp, "jpg");
    }

    public static FileWithPath requestFile(boolean isTemp, String format) {
        String name = DateUtils.getYear() + "/" + DateUtils.getMonth() + "/" + DateUtils.getDay() + "/" + UUID.randomUUID().toString() + "." + format;
        name = isTemp ? "temp/" + name : name;
        File file = new File("./files/" + name);
        file.getParentFile().mkdirs();
        return new FileWithPath(file, name);
    }

    public static String save(byte[] bytes, boolean isTemp) {
        FileWithPath file = requestFile(isTemp);
        try {
            FileUtils.writeBytesToFile(bytes, file.getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getName();
    }
}
