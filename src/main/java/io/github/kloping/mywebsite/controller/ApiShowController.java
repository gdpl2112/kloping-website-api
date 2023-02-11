package io.github.kloping.mywebsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.mapper.ApiDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api")
public class ApiShowController implements ApplicationRunner {
    @Autowired
    ApiDetailMapper mapper;

    @RequestMapping("/getApiList")
    public List<ApiDetail> m1() {
        List<ApiDetail> details = mapper.selectList(new QueryWrapper<>());
        for (ApiDetail detail : details) {
            detail.setDetail("").setSimpleUrl("")
                    .setAddress("");
        }
        return details;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}