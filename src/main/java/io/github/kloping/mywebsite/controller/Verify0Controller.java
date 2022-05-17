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
import java.util.Map;
import java.util.UUID;

/**
 * @author github.kloping
 */
@RestController
public class Verify0Controller {

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

    @RequestMapping("/verify1")
    public String v1(@RequestParam("code") String code) throws IOException {
        if (v0(code)) {
            String uuid = UUID.randomUUID().toString();
            String fn = uuid + ".jar";
            File source = new File("./M1.jar");
            File target = new File(SOURCE_DIR, fn);
            MyUtils.copyFileUsingStream(source, target);
            InterceptorBroadcast.INSTANCE.add(new InterceptorBroadcast.OnceInterceptorReceiver() {
                @Override
                public boolean onReceive(String ip, String url, Map<String, String[]> map, HttpServletRequest request) {
                    if (url.contains(fn)) {
                        target.delete();
                        return true;
                    }
                    return false;
                }
            });
            return fn;
        } else {
            return null;
        }
    }
}
