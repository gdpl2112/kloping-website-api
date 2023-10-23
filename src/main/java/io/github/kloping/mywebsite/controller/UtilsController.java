package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.github.kloping.date.DateUtils;
import io.github.kloping.file.FileUtils;
import io.github.kloping.io.ReadUtils;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.entitys.FileWithPath;
import io.github.kloping.mywebsite.entitys.OnlyData;
import io.github.kloping.mywebsite.entitys.baiduShitu.BaiduShitu;
import io.github.kloping.mywebsite.entitys.baiduShitu.response.BaiduShituResponse;
import io.github.kloping.mywebsite.entitys.database.PwdKeyValue;
import io.github.kloping.mywebsite.mapper.PwdKeyValueMapper;
import io.github.kloping.mywebsite.plugins.detail.BaiduShituDetail;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.github.kloping.mywebsite.plugins.PluginsSource.iBaiduShitu;

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

    public static String getRedirectUrl(String url, String referer) throws IOException {
        Document doc = Jsoup.connect(url).ignoreContentType(true)
                .ignoreHttpErrors(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36 Edg/105.0.1343.50")
//                .header("Host", host)
                .header("Referer", referer)
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Accept", "*/*")
//                .header("Accept-Encoding", "gzip, deflate, br")
//                .header("Connection", "keep-alive")
                .get();
        return doc.location();
    }

    @GetMapping("/getMCloud3")
    public String getMCloud3Url() throws Exception {
        Connection connection = Jsoup.connect("http://fy4.nsmc.org.cn/nsmc/cn/image/area.html").ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.30");
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

    public interface Notice {
        /**
         * on notice
         *
         * @param packName
         * @param title
         * @param text
         */
        void notice(String packName, String title, String text);
    }

    @GetMapping("/tool/ok")
    public String ok(String a) {
        return "ok";
    }


    @Autowired
    PwdKeyValueMapper pkvMapper;

    @GetMapping("/put")
    public String put(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("pwd") String pwd) {
        String oldValue = "";
        QueryWrapper<PwdKeyValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pwd", pwd);
        queryWrapper.eq("k", key);
        PwdKeyValue pkv = pkvMapper.selectOne(queryWrapper);
        if (pkv == null) {
            pkv = new PwdKeyValue();
            pkv.setPwd(pwd);
            pkv.setValue(value);
            pkv.setK(key);
            return pkvMapper.insert(pkv) > 0 ? "OK" : oldValue;
        } else {
            oldValue = pkv.getValue();
            pkv.setPwd(pwd);
            pkv.setValue(value);
            pkv.setK(key);
            UpdateWrapper<PwdKeyValue> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("k", key);
            updateWrapper.eq("pwd", pwd);
            return pkvMapper.update(pkv, updateWrapper) > 0 ? oldValue : "ERROR";
        }
    }

    @GetMapping("/get")
    public String get(@RequestParam("key") String key, @RequestParam("pwd") String pwd) {
        QueryWrapper<PwdKeyValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pwd", pwd);
        queryWrapper.eq("k", key);
        PwdKeyValue pkv = pkvMapper.selectOne(queryWrapper);
        if (pkv == null) {
            return "";
        } else {
            return pkv.getValue();
        }
    }

    @RequestMapping("/list")
    public Object get(@RequestParam("pwd") String pwd) {
        return pkvMapper.selectKeys(pwd);
    }

    @GetMapping("/del")
    public String del(@RequestParam("key") @Nullable String key, @RequestParam("pwd") String pwd) {
        QueryWrapper<PwdKeyValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pwd", pwd);
        if (key != null && !key.isEmpty()) {
            queryWrapper.eq("k", key);
        }
        return pkvMapper.delete(queryWrapper) > 0 ? "OK" : "ERROR";
    }

    @GetMapping("/containsKeys")
    public Integer contains(@RequestParam("keys") String[] keys, @RequestParam("pwd") String pwd, @RequestParam("value") @Nullable String value) {
        Integer c = 0;
        for (String key : keys) {
            c = getContainsCount(pwd, value, c, key);
            continue;
        }
        return c;
    }

    private Integer getContainsCount(String pwd, String value, Integer c, String key) {
        QueryWrapper<PwdKeyValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pwd", pwd);
        queryWrapper.eq("k", key);
        PwdKeyValue pkv = pkvMapper.selectOne(queryWrapper);
        if (pkv == null) return c;
        if (value != null && !value.isEmpty()) {
            if (pkv.getValue().equalsIgnoreCase(value)) {
                c++;
            }
        } else {
            c++;
        }
        return c;
    }

    @GetMapping("/containsPwds")
    public Integer containsPwds(@RequestParam("key") String key, @RequestParam("pwds") String[] pwds, @RequestParam("value") @Nullable String value) {
        Integer c = 0;
        for (String pwd : pwds) {
            c = getContainsCount(pwd, value, c, key);
            continue;
        }
        return c;
    }

    public static final List<Notice> NOTICES = new ArrayList<>();

    @GetMapping("/notice")
    public String notice(@RequestParam String packName, @RequestParam String title, @RequestParam String text) {
        for (Notice notice : NOTICES) {
            notice.notice(packName, title, text);
        }
        return "success";
    }

    @Value("${auth.pwd}")
    String pwd;

    @PostMapping("/uploadImg")
    public String upload(@RequestParam("key") String key, @RequestBody OnlyData data, HttpServletRequest request) {
        if (!this.pwd.equals(pwd)) return "wrong password";
        byte[] bytes = Base64.getDecoder().decode(data.getData().toString());
        String name = save(bytes, false);
        return name;
    }

    @PostMapping("/uploadImg0")
    public String upload(@RequestParam("file") @Nullable MultipartFile imageFile) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                FileWithPath fwp = UtilsController.requestFile(false, "jpg");
                FileOutputStream fos = new FileOutputStream(fwp.getFile());
                fos.write(imageFile.getBytes());
                fos.close();
                return "/" + fwp.getName();
            } else {
                return "error";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/transImg")
    public String proxy(@RequestParam("url") String url, @Nullable @RequestParam("type") String type, HttpServletResponse response) {
        try {
            Connection connection = null;
            connection = org.jsoup.Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).header("Host", new URL(url).getHost()).header("accept-encoding", "gzip, deflate, br").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36 Edg/100.0.1185.50").method(Connection.Method.GET);
            Connection.Response resp = connection.execute();
            byte[] bytes = resp.bodyAsBytes();
            String name = save(bytes, true);
            if (Judge.isEmpty(type)) {
                response.sendRedirect("/" + name);
            } else if ("url".equals(type)) {
                return "http://kloping.top/" + name;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/transImg2")
    public String proxy2(@RequestParam("url") String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            Connection connection = null;
            connection = org.jsoup.Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).header("Host", new URL(url).getHost()).header("accept-encoding", "gzip, deflate, br").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36 Edg/100.0.1185.50").method(Connection.Method.GET);
            Connection.Response resp = connection.execute();
            byte[] bytes = resp.bodyAsBytes();
            String name = save(bytes, true);
            BaiduShitu baiduShitu = BaiduShituDetail.get("http://kloping.top/" + name);
            BaiduShituResponse response0 = iBaiduShitu.response(baiduShitu.getData().getSign());
            return response0.getData().getImageUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
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

    @GetMapping("/stamp2time")
    public String getHost(@RequestParam("stamp") Long stamp, @RequestParam("exp") @Nullable String exp) {
        exp = exp == null ? "yyyy年MM月dd日 HH:mm:ss" : exp;
        return new SimpleDateFormat(exp).format(new Date(stamp));
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
