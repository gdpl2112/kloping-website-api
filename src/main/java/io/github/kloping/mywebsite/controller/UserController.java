package io.github.kloping.mywebsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.entitys.database.UserTemp;
import io.github.kloping.mywebsite.mapper.UserTempMapper;
import io.github.kloping.url.UrlUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    UserTempMapper mapper;

    @GetMapping("/req0")
    public String req(@Param("qid") String qid) {
        String code = UrlUtils.getStringFromHttpUrl(url + "/requestCode0?qid=" + qid + "&pwd=" + pwd);
        Integer c0 = Integer.valueOf(code.trim());
        if (c0 <= 0) return "error";
        QueryWrapper<UserTemp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qid", qid);
        UserTemp temp = mapper.selectOne(queryWrapper);
        if (temp == null) {
            UserTemp t0 = new UserTemp();
            t0.setQid(Long.parseLong(qid));
            t0.setCode(c0);
            mapper.insert(t0);
        } else {
            UserTemp t0 = new UserTemp();
            t0.setQid(Long.parseLong(qid));
            t0.setCode(c0);
            mapper.updateById(t0);
        }
        return "ok";
    }
}
