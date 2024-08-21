package io.github.kloping.mywebsite.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.mywebsite.domain.po.ApiDetail;
import io.github.kloping.mywebsite.mapper.ApiDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api")
public class ApiShowController   {
    @Autowired
    ApiDetailMapper mapper;

    @RequestMapping("/list")
    public List<ApiDetail> m1() {
        List<ApiDetail> details = mapper.selectList(new QueryWrapper<>());
        for (ApiDetail detail : details) {
            detail.setDetail("").setSimpleUrl("").setAddress("");
        }
        return details;
    }

    private Map<String, ApiDetail> detailMap = new LinkedHashMap<>();

    @PostMapping("/d")
    public ApiDetail m1(HttpServletRequest request, String name) {
        String host = request.getHeader("Host");
        name = URLDecoder.decode(name);
        synchronized (detailMap) {
            if (detailMap.isEmpty()) {
                List<ApiDetail> details = mapper.selectList(new QueryWrapper<>());
                for (ApiDetail detail : details) {
                    detail.setAddress(String.format(ApiToolController.HTTP_FORMAT, host, detail.getAddress()));
                    detailMap.put(detail.getName(), detail);
                }
            }
        }
        ApiDetail apiDetail = detailMap.get(name);
        return apiDetail;
    }

    private static final ApiDetail ERR = new ApiDetail().setAddress("请求错误").setDesc("错误").setName("错误 ");
}