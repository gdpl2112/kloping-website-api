package io.github.kloping.mywebsite.controller;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.entitys.ApiDetailM;
import io.github.kloping.mywebsite.entitys.FileWithPath;
import io.github.kloping.mywebsite.entitys.ImageE0;
import io.github.kloping.mywebsite.utils.ImageDrawerUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import static io.github.kloping.mywebsite.controller.UtilsController.requestFile;
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
        //=============
        ApiShowController.LIST.add(new ApiDetailM()
                .setName("摇啊摇(动表情包)")
                .setState("success")
                .setDesc("根据QQ号生成表情包")
        );
        ApiShowDetailController.LIST.add(
                new ApiDetail()
                        .setName("摇啊摇(动表情包)")
                        .setState("success")
                        .setDesc("根据QQ号生成表情包")
                        .setAddress("/api/image/yao2yao?qid=qq")
                        .setDetail("qid QQ号")
                        .setSimpleUrl("/api/image/yao2yao?qid=3474006766")
        );
    }

    @RequestMapping("/tong")
    public String tong(@RequestParam("q1") String q1, @RequestParam("q2") String q2, HttpServletResponse response) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(TONG_BASE_BYTES));
            BufferedImage iq1 = ImageIO.read(new URL(String.format("https://q1.qlogo.cn/g?b=qq&nk=%s&s=640", q1)));
            BufferedImage iq2 = ImageIO.read(new URL(String.format("https://q1.qlogo.cn/g?b=qq&nk=%s&s=640", q2)));
            int size = 84;
            iq1 = (BufferedImage) ImageDrawerUtils.image2Size(iq1, size, size);
            iq2 = (BufferedImage) ImageDrawerUtils.image2Size(iq2, size + 6, size + 6);
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

    @RequestMapping("/yao2yao")
    public String yao2yao(@RequestParam("qid") String q1, HttpServletResponse response) throws Exception {
        FileWithPath outFile = requestFile(true, "gif");
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outFile.getFile().getAbsolutePath());
        encoder.setRepeat(0);
        encoder.setQuality(5);
        encoder.setFrameRate(200);
        double w = (Math.PI / 12);
        int[] is = new int[]{4, 3, 0, 1, 2};
        final BufferedImage oImage = ImageIO.read(new URL("http://q1.qlogo.cn/g?b=qq&nk=" + q1 + "&s=640").openStream());
        for (int i = 0; i < 24; i++) {
            int width = 200;
            int height = 200;
            BufferedImage base = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics g = base.getGraphics();
            g.setClip(0, 0, width, height);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.BLACK);
            g.dispose();
            ImageE0[] es = new ImageE0[5];

            BufferedImage bi = (BufferedImage) ImageDrawerUtils.image2Size(oImage, 120, 120);
            bi = (BufferedImage) ImageDrawerUtils.roundImage(bi, 999);

            double end;
            double d0;
            end = 0.0;
            d0 = w * i;
            end = Math.sin(d0);
            end *= 10;

            float e0 = (float) (end * 2);
            bi = (BufferedImage) ImageDrawerUtils.rotateImage(bi, e0);
            es[0] = new ImageE0(bi, (int) (41 + e0), 56);

            for (int i1 = 1; i1 <= 4; i1++) {
                es[i1] = getIndex(oImage, base, end, i);
                d0 = w * (i - (i1 * 6));
                end = Math.sin(d0);
                end *= 10;
            }

            for (int i1 : is) {
                ImageE0 e = es[i1];
                if (e == null) continue;
                base = ImageDrawerUtils.putImage(base, e.getImage(), e.getX(), e.getY());
            }
            if (i == 6) {
                is[1] = 1;
                is[3] = 3;
            }
            if (i == 12) {
                is[0] = 2;
                is[4] = 4;
            }
            if (i == 12) {
                is[0] = 2;
                is[4] = 4;
            }
            if (i == 18) {
                is[1] = 3;
                is[3] = 1;
            }
            encoder.setDelay(100);
            encoder.addFrame(base);
        }
        encoder.finish();
        return "http://kloping.top/" + outFile.getName();
    }

    @NotNull
    private static ImageE0 getIndex(BufferedImage oImage, BufferedImage base, double end, int i) throws IOException {
        int e1 = (int) (end);
        BufferedImage i1 = (BufferedImage) ImageDrawerUtils.image2Size(oImage, 50 + e1, 50 + e1);
        i1 = (BufferedImage) ImageDrawerUtils.roundImage(i1, 999);
        int x1 = (int) (78 + (end * 6));
        double w = (Math.PI / 6);
        double d0 = w * i;
        end = Math.sin(d0);
        end *= 10;
        int y1 = (int) (36 + (end * 0.6));
        return new ImageE0(i1, x1, y1);
    }

}