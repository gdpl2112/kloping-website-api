package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.entitys.ApiDetailM;
import io.github.kloping.mywebsite.utils.ImageDrawerUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import static io.github.kloping.mywebsite.controller.UtilsController.save;
import static io.github.kloping.mywebsite.utils.ImageDrawerUtils.TONG_BASE_BYTES;

/**
 * @author github.kloping
 */
@RestController
@RequestMapping("/api/image")
public class ApiImageController {
    static {
        ApiShowController.LIST.add(new ApiDetailM()
                .setName("捅刀子(表情包)")
                .setState("success")
                .setDesc("根据QQ号生成表情包")
        );
        ApiShowDetailController.LIST.add(
                new ApiDetail()
                        .setName("捅刀子(表情包)")
                        .setState("success")
                        .setDesc("根据QQ号生成表情包")
                        .setAddress("/api/image/tong?q1=主动&q2=被动")
                        .setDetail("q1捅刀子的人,q2被捅刀子的人")
                        .setSimpleUrl("/api/image/tong?q1=3474006766&q2=930204019")
        );
    }

    @RequestMapping("/tong")
    public String tong(@RequestParam("q1") String q1, @RequestParam("q2") String q2, HttpServletResponse response) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(TONG_BASE_BYTES));
            BufferedImage iq1 = ImageIO.read(new URL(String.format("https://q1.qlogo.cn/g?b=qq&nk=%s&s=640", 3474006766L)));
            BufferedImage iq2 = ImageIO.read(new URL(String.format("https://q1.qlogo.cn/g?b=qq&nk=%s&s=640", 930204019L)));
            int size = 84;
            iq1 = (BufferedImage) ImageDrawerUtils.image2Size(iq1, size, size);
            iq2 = (BufferedImage) ImageDrawerUtils.image2Size(iq2, size+6, size+6);
            image = ImageDrawerUtils.putImage(image, iq1, 108, 18);
            image = ImageDrawerUtils.putImage(image, iq2, 16, 10);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            String name = save(baos.toByteArray(), true);
            return "http://kloping.top/" + name;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "失败";
    }
}
