package io.github.kloping.mywebsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.mapper.ApiDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author github-kloping
 */
@RestController
public class ApiShowDetailController implements ApplicationRunner {

    public static final String HTTP_FORMAT = "http://%s%s";
    public static final String HTTP_FORMAT1 = "http://%s/%s";

    @Autowired
    ApiDetailMapper mapper;

    private Map<String, ApiDetail> detailMap = new LinkedHashMap<>();

    @PostMapping("/apid")
    public ApiDetail m1(HttpServletRequest request, String name) {
        String host = request.getHeader("Host");
        name = URLDecoder.decode(name);
        synchronized (detailMap) {
            if (detailMap.isEmpty()) {
                List<ApiDetail> details = mapper.selectList(new QueryWrapper<>());
                for (ApiDetail detail : details) {
                    detail.setAddress(String.format(HTTP_FORMAT, host, detail.getAddress()));
                    detailMap.put(detail.getName(), detail);
                }
            }
        }
        ApiDetail apiDetail = detailMap.get(name);
        return apiDetail;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }

    private static final ApiDetail ERR = new ApiDetail().setAddress("请求错误").setDesc("错误").setName("错误 ");
}
