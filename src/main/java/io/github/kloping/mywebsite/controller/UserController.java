package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.MyWebSiteApplication;
import io.github.kloping.mywebsite.broadcast.WebHookBroadcast;
import io.github.kloping.mywebsite.entitys.database.FriendLink;
import io.github.kloping.mywebsite.entitys.database.UserTemp;
import io.github.kloping.mywebsite.entitys.database.Verify0Entity;
import io.github.kloping.mywebsite.mapper.FriendLinkMapper;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import io.github.kloping.mywebsite.mapper.Verify0Mapper;
import io.github.kloping.mywebsite.utils.EmailSender;
import io.github.kloping.mywebsite.utils.KaptchaUtils;
import io.github.kloping.mywebsite.webhook.e0.OrderReq;
import io.github.kloping.url.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.github.kloping.mywebsite.config.UserDetailsServiceImpl.EMAIL_TYPE;

/**
 * @author github.kloping
 */
@RestController
public class UserController {
    public UserController(FriendLinkMapper friendLinkMapper, UserTempMapper userTempMapper, Verify0Mapper verify0Mapper) {
        this.friendLinkMapper = friendLinkMapper;
        WebHookBroadcast.INSTANCE.add(this::onReceive);
        this.userTempMapper = userTempMapper;
        this.verify0Mapper = verify0Mapper;
    }

    private void onReceive(OrderReq req) {
        if (req == null) return;
        try {
            if (!req.getData().getOrder().getPlan_title().equals("获得授权码")) {
                return;
            }
            String remark = req.getData().getOrder().getRemark().trim();
            Integer month = req.getData().getOrder().getMonth().intValue();

            UserTemp temp = userTempMapper.selectById(remark);
            StringBuilder sb = new StringBuilder();
            if (temp != null) {
                Verify0Entity entity = verify0Mapper.selectByCode(temp.getAuth());
                if (entity == null) {
                    entity.setCode(UUID.randomUUID().toString());
                    entity.setExpire(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
                    verify0Mapper.insert(entity);
                }
                entity.setExpire(entity.getExpire() + 1000 * 60 * 60 * 24 * 30);
                verify0Mapper.updateById(entity);
                sb.append(temp.getEid()).append("\n")
                        .append(temp.getQid()).append("\n")
                        .append(temp.getNickname()).append("\n")
                        .append("购买授权成功").append("\n");
            } else {
                if (url == null) {
                    url = MyWebSiteApplication.applicationContext.getEnvironment().getProperty("auth.url").toString();
                }
                if (pwd == null) {
                    pwd = MyWebSiteApplication.applicationContext.getEnvironment().getProperty("auth.pwd").toString();
                }
                sb.append("<At:3474006766>.\n")
                        .append("请注意!授权码信息收集错误\n")
                        .append(JSON.toJSON(req));
            }
            UrlUtils.getStringFromHttpUrl(url + "/say?gid=570700910&pwd=" + pwd + "&s=" + URLEncoder.encode(sb.toString()));
        } catch (Exception e) {
            System.err.println("订单信息错误");
            throw new RuntimeException(e);
        }
    }

    @Value("${auth.url}")
    String url;

    @Value("${auth.pwd}")
    String pwd;

    final UserTempMapper userTempMapper;

    public static final Map<String, Long> eid2cd = new LinkedHashMap<>();
    public static final Map<String, String> eid2code = new LinkedHashMap<>();

    @RequestMapping("/reg")
    public String req(@RequestParam("eid") String eid, @RequestParam("qid") String qid, @RequestParam("pwd") String pwd, @RequestParam("name") String name, @RequestParam("code") String code) {
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

    @GetMapping("/req0")
    public String req0(@RequestParam("eid") String eid) {
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
        userTemp.setPwd("");
        return userTemp;
    }

    final Verify0Mapper verify0Mapper;

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


    final FriendLinkMapper friendLinkMapper;

    private List<FriendLink> links = null;

    @GetMapping("/flinks")
    public List<FriendLink> friendLinkList() {
        synchronized (friendLinkMapper) {
            if (links == null) {
                links = friendLinkMapper.selectList(null);
            }
            int size = friendLinkMapper.selectCount(null);
            if (links.size() != size) {
                links = friendLinkMapper.selectList(null);
            }
        }
        return links;
    }
}
