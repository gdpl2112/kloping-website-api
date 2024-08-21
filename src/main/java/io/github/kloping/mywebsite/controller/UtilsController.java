package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSONObject;
import io.github.kloping.date.DateUtils;
import io.github.kloping.file.FileUtils;
import io.github.kloping.io.ReadUtils;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.domain.bo.FileWithPath;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author github-kloping
 */
@RestController
@CrossOrigin
public class UtilsController {
    @Value("${upload.passwd}")
    private String pwd;

    @GetMapping("/exec")
    public Object exec(HttpServletResponse response, @RequestParam("pwd") String pwd, @RequestParam("cmd") String cmd, @RequestParam("out") Boolean o) {
        try {
            if (!this.pwd.equals(pwd)) return "ERR";
            CountDownLatch cdl = new CountDownLatch(2);
            Process process = Runtime.getRuntime().exec(cmd);
            if (!o) return null;
            String out = ReadUtils.readAll(process.getInputStream(), "utf-8");
            String err = ReadUtils.readAll(process.getErrorStream(), "utf-8");
            JSONObject jo = new JSONObject();
            jo.put("out", out);
            jo.put("err", err);
            return jo;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/tool/ok")
    public String ok(String a) {
        return "ok";
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

    @PostMapping("/upload-img")
    public String upload(HttpServletRequest request, @RequestParam("file") @Nullable MultipartFile imageFile) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                FileWithPath fwp = UtilsController.requestFile(true, "jpg");
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

    @PostMapping("/editormd-image-file")
    public String uploadEd(HttpServletResponse response, @RequestParam(value = "editormd-image-file", required = false) MultipartFile imageFile) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (imageFile != null && !imageFile.isEmpty()) {
            FileWithPath fwp = UtilsController.requestFile(true, "jpg");
            FileOutputStream fos = new FileOutputStream(fwp.getFile());
            fos.write(imageFile.getBytes());
            fos.close();
            String url = fwp.getName();
            jsonObject.put("success", 1);
            jsonObject.put("message", "OK");
            jsonObject.put("url", url);
            System.out.println("md上传:" + url);
        } else {
            jsonObject.put("success", 0);
        }
        return jsonObject.toString();
    }

    @GetMapping("/trans-img")
    public String uploadImg(@RequestParam("url") String url, @Nullable @RequestParam("type") String type, HttpServletResponse response
            , HttpServletRequest request) {
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
        String name = "/" + DateUtils.getYear() + "/" + DateUtils.getMonth() + "/" + DateUtils.getDay() + "-" + UUID.randomUUID().toString() + "." + format;
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
