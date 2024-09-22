package io.github.kloping.mywebsite.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kloping.file.FileUtils;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.controller.UtilsController;
import io.github.kloping.mywebsite.domain.bo.FileWithPath;
import io.github.kloping.mywebsite.domain.bo.position.PositionInfo;
import io.github.kloping.mywebsite.domain.po.AddressCode;
import io.github.kloping.mywebsite.mapper.AddressCodeMapper;
import io.github.kloping.mywebsite.services.IgetLngLat;
import io.github.kloping.url.UrlUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author github.kloping
 */
@RestController
@RequestMapping("/api")
public class ApiToolController {

    public static final String HTTP_FORMAT = "http://%s%s";
    public static final String HTTP_FORMAT1 = "http://%s/%s";

    @RequestMapping("/ocr")
    public Object ocr(@RequestParam("url") @Nullable String url, @RequestParam("file") @Nullable MultipartFile imageFile) {
        try {
            byte[] bytes = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                bytes = imageFile.getBytes();
            } else if (Judge.isNotEmpty(url)) {
                bytes = UrlUtils.getBytesFromHttpUrl(url);
            } else return new ArrayList<>();
            String json = Jsoup.connect("https://api.pearktrue.cn/api/ocr/index.php"
                    ).method(Connection.Method.POST).ignoreContentType(true).ignoreHttpErrors(true)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.78")
                    .header("Accept", "*/*")
                    .data("file", "def.jpg", new ByteArrayInputStream(bytes), "image/png")
                    .execute().body();
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Autowired
    AddressCodeMapper acMapper;

    @Autowired
    IgetLngLat igetLngLat;

    @RequestMapping("/acode")
    public Object aCode(@RequestParam("name") String name) {
        QueryWrapper<AddressCode> qw = new QueryWrapper<>();
        qw.likeRight("c_name", name);
        List<AddressCode> list = acMapper.selectList(qw);
        if (!list.isEmpty()) return list.get(0);
        else {
            try {
                PositionInfo info = igetLngLat.get(name);
                return info.getDetail().getAdcode();
            } catch (Exception e) {
                e.printStackTrace();
                return "{}";
            }
        }
    }
}
