package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.broadcast.InterceptorBroadcast;
import io.github.kloping.mywebsite.entitys.VerifyFile;
import io.github.kloping.mywebsite.entitys.database.UserTemp;
import io.github.kloping.mywebsite.entitys.database.Verify0Entity;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import io.github.kloping.mywebsite.mapper.Verify0Mapper;
import io.github.kloping.mywebsite.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @RequestMapping("/getVerify0")
    public Verify0Entity gv0(@RequestParam("code") String code) {
        Verify0Entity entity = mapper.selectByCode(code);
        if (entity != null) return entity;
        else return new Verify0Entity().setCode("abcd").setExpire(1L);
    }

    @Autowired
    UserTempMapper userTempMapper;

    @RequestMapping("/userv")
    public Verify0Entity userv( @AuthenticationPrincipal UserDetails userDetails) {
        UserTemp userTemp = userTempMapper.selectById(userDetails.getUsername());
        Verify0Entity entity = mapper.selectByCode(userTemp.getAuth());
        if (entity != null) return entity;
        else return new Verify0Entity().setCode("abcd").setExpire(1L);
    }

    public static final File SOURCE_DIR = new File("./files");

    public static final Map<String, VerifyFile> FN2FILE = new HashMap<>();

    @RequestMapping("/verify1")
    public synchronized String v1(@RequestParam("code") String code) throws IOException {
        if (v0(code)) {
            String uuid = UUID.randomUUID().toString();
            String fn = uuid + ".jar";
            VerifyFile file = new VerifyFile() {
                @Override
                public void over() {
                    getFile().delete();
                    FN2FILE.remove(fn);
                }
            };
            file.setUuid(uuid);
            File source = new File("./M1.jar");
            File target = new File(SOURCE_DIR, fn);
            file.setFile(target);
            MyUtils.copyFileUsingStream(source, target);
            file.setNum(0).setCode(code);
            file.setTime(120000L);
            file.start();
            FN2FILE.put(fn, file);
            return fn;
        } else {
            return null;
        }
    }
}
