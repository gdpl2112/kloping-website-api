package io.github.kloping.mywebsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.entitys.database.UserTemp;
import io.github.kloping.mywebsite.entitys.database.Verify0Entity;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import io.github.kloping.mywebsite.mapper.Verify0Mapper;
import io.github.kloping.mywebsite.utils.EmailSender;
import io.github.kloping.mywebsite.utils.KaptchaUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author github.kloping
 */
@RestController
public class UserController {

    @Value("${auth.url}")
    String url;

    @Value("${auth.pwd}")
    String pwd;

    @Autowired
    UserTempMapper userTempMapper;

    public static final Map<String, Long> eid2cd = new LinkedHashMap<>();
    public static final Map<String, String> eid2code = new LinkedHashMap<>();

    @RequestMapping("/reg")
    public String req(@Param("eid") String eid, @Param("qid") String qid, @Param("pwd") String pwd, @Param("name") String name, @Param("code") String code

    ) {
        if (userTempMapper.selectById(eid) != null) return "邮箱已注册!";
        QueryWrapper<UserTemp> qw = new QueryWrapper<>();
        qw.eq("qid", qid);
        if (userTempMapper.selectOne(qw) != null) return "QQ已被绑定!";
        if (pwd == null || pwd.isEmpty() || pwd.length() > 12 || pwd.length() <= 6) return "密码长度不能大于12或小于6";
        if (!eid2code.get(eid).equals(code)) return "验证码错误";
        name = (name == null || name.trim().isEmpty()) ? "默认昵称" : name;
        UserTemp userTemp = new UserTemp().setEid(eid).setQid(Long.valueOf(qid)).setNickname(name).setPwd(pwd).setAuth("")
                .setRegt(System.currentTimeMillis());
        userTempMapper.insert(userTemp);
        eid2code.remove(eid);
        return "注册成功";
    }

    @GetMapping("/req0")
    public String req0(@Param("eid") String eid) {
        if (eid2cd.containsKey(eid)) {
            if (eid2cd.get(eid) + 60000 > System.currentTimeMillis()) {
                return "请求验证码失败;请稍后重试!";
            }
        }
        String code = KaptchaUtils.getNumberCode();
        boolean k = EmailSender.sendEmail(eid, "您的验证码", "<h1>" + "您的验证码是: " + code + "</h1>");
        if (k) {
            eid2code.put(eid, code);
            eid2cd.put(eid, System.currentTimeMillis());
            return "验证码发送成功";
        } else {
            return "发送验证码失败;请检查邮箱是否正确或稍后再试";
        }
    }

    @GetMapping("/login_state")
    public String state(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails == null ? "false" : "true";
    }

    @GetMapping("/user")
    public UserTemp user(@AuthenticationPrincipal UserDetails userDetails) {
        UserTemp userTemp = userTempMapper.selectById(userDetails.getUsername());
        return userTemp;
    }

    @Autowired
    Verify0Mapper verify0Mapper;

    @GetMapping("/req-try")
    public Verify0Entity rt(@AuthenticationPrincipal UserDetails userDetails) {
        UserTemp userTemp = userTempMapper.selectById(userDetails.getUsername());
        if (userTemp.getAuth() == null || userTemp.getAuth().isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            Verify0Entity entity = new Verify0Entity();
            entity.setExpire(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7);
            entity.setCode(uuid);
            verify0Mapper.insert(entity);
            userTemp.setAuth(uuid);
            userTempMapper.updateById(userTemp);
            return entity;
        }
        return null;
    }
}
