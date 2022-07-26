package io.github.kloping.mywebsite.controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.github.kloping.mywebsite.Source.println;
import static io.github.kloping.mywebsite.Source.updateMap;

/**
 * @author github-kloping
 */
@RestController
public class UtilsController {

    @GetMapping("/getNum")
    public String getAllNum() {
        return "自上次重启 已经被访问了" + SystemController.AllNum + "次";
    }

    private static String key;
    private static String MyKey = "hrskloping";

    static {
        key = new Random().nextInt() + "";
        println(key);
    }

    public static int t1 = 1;
    public static int t2 = 1;

    @GetMapping("/abo")
    public void addTimes(Long id, String key) {
        if (key.equals(UtilsController.key) || key.equals(MyKey)) {
            if (id == 291841860) {
                t1++;
                println("add t1 => " + t1);
            } else {
                t2++;
                println("add t2 => " + t2);
            }
            updateMap();
        } else {
            System.err.println(key + " Error");
        }
    }

    @GetMapping("getAllInfo")
    public String getAllInfo() {
        return "[{\"id\":291841860,\"times\":\"001号今天累计处理信息" + t1 + "条=\"},{\"id\":3597552450,\"times\":\"017号今天累计处理信息" + t2 + "条=\"}]";
    }

    @GetMapping("/getRandPng")
    public String getUrl(@RequestHeader(value = "User-Agent") String userAgent, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String url;
        if (userAgent.contains("Android") || userAgent.contains("iPhone")) {
            url = getRedirectUrl("https://api.uomg.com/api/rand.img2?sort=%E4%BA%8C%E6%AC%A1%E5%85%83&format=images");
        } else {
            url = getRedirectUrl("https://api.uomg.com/api/rand.img1?sort=%E4%BA%8C%E6%AC%A1%E5%85%83&format=images");
        }
        httpServletResponse.sendRedirect(url);
        return url;
//        httpServletResponse.setContentType("image/png");
//        OutputStream os = httpServletResponse.getOutputStream();
//        byte[] bytes = new byte[0];
//        BufferedImage image = ImageIO.read(getRandFile());
//        if (userAgent.contains("Android") || userAgent.contains("iPhone")) {
//            image = MyUtils.rotateImage(image, 90);
//        }
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(image, "png", baos);
//        bytes = baos.toByteArray();
//        os.write(bytes);
//        os.flush();
//        os.close();
//        return "ok";
    }

    public static File[] files = new File("./imgs").listFiles();
    public static final Random RAND = new Random();

    private File getRandFile() {
        files = new File("./imgs").listFiles();
        return files[RAND.nextInt(files.length)];
    }

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
        System.out.println(a);
        return "ok";
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
}
