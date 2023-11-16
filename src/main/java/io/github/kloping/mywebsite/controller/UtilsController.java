package io.github.kloping.mywebsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.github.kloping.date.DateUtils;
import io.github.kloping.file.FileUtils;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.entitys.FileWithPath;
import io.github.kloping.mywebsite.mapper.PwdKeyValueMapper;
import io.github.kloping.mywebsite.mapper.dao.PwdKeyValue;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
                .header("Referer", referer)
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Accept", "*/*")
                .get();
        return doc.location();
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

    @GetMapping("/contains-keys")
    public Integer contains(@RequestParam("keys") String[] keys, @RequestParam("pwd") String pwd, @RequestParam("value") @Nullable String value) {
        Integer c = 0;
        for (String key : keys) {
            c = getContainsCount(pwd, value, c, key);
            continue;
        }
        return c;
    }

    @GetMapping("/contains-pwds")
    public Integer containsPwds(@RequestParam("key") String key, @RequestParam("pwds") String[] pwds, @RequestParam("value") @Nullable String value) {
        Integer c = 0;
        for (String pwd : pwds) {
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

    @Value("${auth.pwd}")
    String pwd;


    @PostMapping("/upload-img")
    public String upload(HttpServletRequest request, @RequestParam("file") @Nullable MultipartFile imageFile) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                FileWithPath fwp = UtilsController.requestFile(false, "jpg");
                FileOutputStream fos = new FileOutputStream(fwp.getFile());
                fos.write(imageFile.getBytes());
                fos.close();
                return getHostWithPre(request) + fwp.getName();
            } else {
                return "error";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/trans-img")
    public String proxy(@RequestParam("url") String url,
                        @Nullable @RequestParam("type") String type
            , HttpServletResponse response
            , HttpServletRequest request
    ) {
        try {
            Connection connection = null;
            connection = org.jsoup.Jsoup.connect(url).ignoreContentType(true)
                    .ignoreHttpErrors(true).header("Host", new URL(url).getHost())
                    .header("accept-encoding", "gzip, deflate, br")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36 Edg/100.0.1185.50")
                    .method(Connection.Method.GET);
            Connection.Response resp = connection.execute();
            byte[] bytes = resp.bodyAsBytes();
            String name = save(bytes, true);
            if (Judge.isEmpty(type)) {
                response.sendRedirect("/" + name);
            } else if ("url".equals(type)) {
                return getHostWithPre(request) + name;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/stamp2time")
    public String getHost(@RequestParam("stamp") Long stamp, @RequestParam("exp") @Nullable String exp) {
        exp = exp == null ? "yyyy年MM月dd日 HH:mm:ss" : exp;
        return new SimpleDateFormat(exp).format(new Date(stamp));
    }

    @GetMapping("/get-host")
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

    public static String getHost(HttpServletRequest request) {
        String host = request.getHeader("host");
        if (Judge.isEmpty(host)) host = request.getHeader("Host");
        return host;
    }

    public static String getHostWithPre(HttpServletRequest request) {
        return "http://" + getHost(request);
    }

    public static FileWithPath requestFile(boolean isTemp) {
        return requestFile(isTemp, "jpg");
    }

    public static FileWithPath requestFile(boolean isTemp, String format) {
        String name = "/" + DateUtils.getYear() + "/" + DateUtils.getMonth() + "/" + DateUtils.getDay() + "/" + UUID.randomUUID().toString() + "." + format;
        name = isTemp ? "/temp" + name : name;
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
