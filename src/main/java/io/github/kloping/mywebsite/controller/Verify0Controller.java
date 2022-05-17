package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.broadcast.InterceptorBroadcast;
import io.github.kloping.mywebsite.entitys.Verify0Entity;
import io.github.kloping.mywebsite.mapper.Verify0Mapper;
import io.github.kloping.mywebsite.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author github.kloping
 */
@RestController
public class Verify0Controller implements InterceptorBroadcast.InterceptorReceiver {

    public Verify0Controller() {
        InterceptorBroadcast.INSTANCE.add(this);
    }

    @Override
    public synchronized boolean onReceive(String ip, String url, Map<String, String[]> map, HttpServletRequest request) {
        if (url.endsWith(".jar")) {
            String fn = url.substring(url.lastIndexOf("/") + 1).trim();
            if (FN2NUM.containsKey(fn)) {
                FN2NUM.put(fn, FN2NUM.get(fn) + 1);
            }
            if (FN2NUM.get(fn) >= 3) {
                new File(SOURCE_DIR, fn).delete();
                FN2NUM.remove(fn);
                CODE2FN.remove(FN2CODE.get(fn));
                FN2CODE.remove(fn);
            }
        }
        return false;
    }

    @Autowired
    Verify0Mapper mapper;

    @RequestMapping("/verify0")
    public Boolean v0(@RequestParam("code") String code) {
        Verify0Entity entity = mapper.selectByCode(code);
        if (entity != null) {
            return System.currentTimeMillis() < entity.getExpire();
        }
        return false;
    }

    public static final File SOURCE_DIR = new File("./files");

    public static final Map<String, Integer> FN2NUM = new HashMap<>();
    public static final Map<String, String> FN2CODE = new HashMap<>();
    public static final Map<String, String> CODE2FN = new HashMap<>();

    @RequestMapping("/verify1")
    public String v1(@RequestParam("code") String code) throws IOException {
        if (v0(code)) {
            if (CODE2FN.containsKey(code)) {
                return CODE2FN.get(code);
            }
            String uuid = UUID.randomUUID().toString();
            String fn = uuid + ".jar";
            File source = new File("./M1.jar");
            File target = new File(SOURCE_DIR, fn);
            MyUtils.copyFileUsingStream(source, target);
            FN2NUM.put(fn, 0);
            CODE2FN.put(code, fn);
            FN2CODE.put(fn, code);
            return fn;
        } else {
            return null;
        }
    }
}
