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
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

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

    @RequestMapping("/mp32amr")
    public Object mp32amr(@RequestParam("url") String url, HttpServletResponse response) {
        try {
            FileWithPath source = UtilsController.requestFile(true, "mp3");
            FileUtils.writeBytesToFile(UrlUtils.getBytesFromHttpUrl(url), source.getFile());
            FileWithPath target = UtilsController.requestFile(true, "amr");
            AudioAttributes audioAttributes = new AudioAttributes();
            audioAttributes.setChannels(1);
            audioAttributes.setBitRate(24000);
            audioAttributes.setSamplingRate(8000);
            EncodingAttributes encodingAttributes = new EncodingAttributes();
            encodingAttributes.setOutputFormat("amr");
            encodingAttributes.setAudioAttributes(audioAttributes);
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source.getFile()), target.getFile(), encodingAttributes);
            response.addHeader("Content-type", "audio/amr-wb");
            response.getOutputStream().write(FileUtils.getBytesFromFile(target.getFile().getAbsolutePath()));
            source.getFile().delete();
            target.getFile().delete();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "转换失败!\n" + e.getMessage();
        }
    }
}
