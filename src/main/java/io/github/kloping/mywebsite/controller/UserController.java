package io.github.kloping.mywebsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.common.Public;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.controller.api.ApiImageController;
import io.github.kloping.mywebsite.domain.po.BgImg;
import io.github.kloping.mywebsite.domain.po.FriendLink;
import io.github.kloping.mywebsite.domain.po.UserTemp;
import io.github.kloping.mywebsite.mapper.BgImgMapper;
import io.github.kloping.mywebsite.mapper.FriendLinkMapper;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import io.github.kloping.mywebsite.utils.EmailConfig;
import io.github.kloping.mywebsite.utils.KaptchaUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.github.kloping.mywebsite.config.UserDetailsServiceImpl.EMAIL_TYPE;

/**
 * @author github.kloping
 */
@RestController
public class UserController {
    @Value("${bot.url}")
    String url;
    @Value("${bot.pwd}")
    String pwd;

    @Autowired
    FriendLinkMapper friendLinkMapper;
    @Autowired
    UserTempMapper userTempMapper;
    @Autowired
    BgImgMapper bgImgMapper;

    public static final Map<String, Long> eid2cd = new LinkedHashMap<>();
    public static final Map<String, String> eid2code = new LinkedHashMap<>();

    @RequestMapping("/reg")
    public String req(@RequestParam("eid") String eid, @RequestParam("qid") String qid,
                      @RequestParam("pwd") String pwd, @RequestParam("name") String name, @RequestParam("code") String code) {
        if (Judge.isEmpty(name) || name.length() < 4 || name.length() > 10) return "昵称为空或长度违规";
        if (userTempMapper.selectById(name) != null) return "昵称已注册!";
        QueryWrapper<UserTemp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("eid", eid);
        if (userTempMapper.selectOne(queryWrapper) != null) return "邮箱已注册!";
        QueryWrapper<UserTemp> qw = new QueryWrapper<>();
        qw.eq("qid", qid);
        if (userTempMapper.selectOne(qw) != null) return "QQ已被绑定!";
        if (pwd == null || pwd.isEmpty() || pwd.length() > 12 || pwd.length() <= 6) return "密码长度不能大于12或小于6";
        if (!eid2code.get(eid).equals(code)) return "验证码错误";
        UserTemp userTemp = new UserTemp().setEid(eid)
                .setQid(Long.valueOf(qid)).setNickname(name).setPwd(pwd).setAuth("")
                .setRegt(System.currentTimeMillis()).setType(EMAIL_TYPE)
                .setIcon("http://q.qlogo.cn/headimg_dl?dst_uin=" + qid + "&spec=640");
        userTempMapper.insert(userTemp);
        eid2code.remove(eid);
        return "注册成功";
    }

    @Resource
    EmailConfig emailConfig;

    @GetMapping("/req0")
    public String req0(@RequestParam("eid") String eid) {
        if (eid2cd.containsKey(eid)) {
            if (eid2cd.get(eid) + 60000 > System.currentTimeMillis()) {
                return "请求验证码失败;请稍后重试!";
            }
        }
        String code = KaptchaUtils.getNumberCode();
        boolean k = emailConfig.sendEmail(eid, "您的验证码", "<h1>" + "您的验证码是: " + code + "</h1>");
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
        userTemp.setPwd("");
        return userTemp;
    }

    @GetMapping("/user0")
    public UserTemp user0(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            UserTemp temp = new UserTemp();
            temp.setState(false);
            return temp;
        } else return user(userDetails);
    }

    private List<FriendLink> links = null;

    @GetMapping("/flinks")
    public List<FriendLink> friendLinkList() {
        synchronized (friendLinkMapper) {
            if (links == null) {
                links = friendLinkMapper.selectList(null);
            }
            int size = Math.toIntExact(friendLinkMapper.selectCount(null));
            if (links.size() != size) {
                links = friendLinkMapper.selectList(null);
            }
        }
        return links;
    }

    @GetMapping("/user_image_list")
    public Object imageList(HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        List<BgImg> list = bgImgMapper.selectListByEid(userDetails.getUsername());
        String host = UtilsController.getHostWithPre(request);
        for (BgImg bgImg : list) {
            bgImg.setU0(host + bgImg.getUrl());
        }
        return list;
    }

    @GetMapping("/del_image")
    public Object delImage(HttpServletRequest request, @RequestParam("url") String url, @AuthenticationPrincipal UserDetails userDetails) {
        bgImgMapper.deleteByEidAndUrl(userDetails.getUsername(), url);
        return imageList(request, userDetails);
    }

    @PostMapping("/upload_image0")
    public String uploadImage0(HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("img") MultipartFile imageFile, @RequestParam("t") String t) {
        try {
            String path = UtilsController.save(imageFile.getBytes(), false);
            Cookie cookie = new Cookie(ApiImageController.R0_KEY, path);
            cookie.setMaxAge(120 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);
            BgImg bgImg = new BgImg();
            bgImg.setEid(userDetails.getUsername());
            bgImg.setType(Integer.valueOf(t));
            bgImg.setUrl(path);
            boolean k = bgImgMapper.insert(bgImg) > 0;
            if (k) Public.EXECUTOR_SERVICE.submit(() -> {
                try {
                    String eu = url + "/say";
                    String tu = "http://kloping.top" + path;
                    Jsoup.connect(eu)
                            .ignoreContentType(true).ignoreHttpErrors(true)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.41")
                            .timeout(30000)
                            .data("gid", "570700910")
                            .data("pwd", pwd)
                            .data("s", String.format("新背景图上传成功!type:%s\nurl:%s", t, tu))
                            .post();
                    Jsoup.connect(eu)
                            .ignoreContentType(true).ignoreHttpErrors(true)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.41")
                            .timeout(30000)
                            .data("gid", "570700910")
                            .data("pwd", pwd)
                            .data("s", String.format("<pic:%s>", tu))
                            .post();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return k ? "上传成功!" : "上传异常";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
