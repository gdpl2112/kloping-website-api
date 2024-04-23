package io.github.kloping.mywebsite.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.madgag.gif.fmsware.AnimatedGifEncoder;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.entitys.FileWithPath;
import io.github.kloping.mywebsite.entitys.ImageE0;
import io.github.kloping.mywebsite.mapper.dao.BgImg;
import io.github.kloping.mywebsite.mapper.BgImgMapper;
import io.github.kloping.mywebsite.utils.ImageDrawerUtils;
import io.github.kloping.mywebsite.utils.BlogCodeUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static io.github.kloping.mywebsite.controller.ApiShowDetailController.HTTP_FORMAT1;
import static io.github.kloping.mywebsite.controller.UtilsController.requestFile;
import static io.github.kloping.mywebsite.controller.UtilsController.save;
import static io.github.kloping.mywebsite.utils.ImageDrawerUtils.TONG_BASE_BYTES;

/**
 * @author github.kloping
 */
@RestController
@RequestMapping("/api/image")
public class ApiImageController {

    public static final String R0_KEY = "randUrl";

    @Autowired
    BgImgMapper bgImgMapper;

    @GetMapping("/rand0")
    public void r0(HttpServletRequest request, HttpServletResponse response, @Nullable @RequestParam("p") Integer p) {
        try {
            String url = BlogCodeUtils.getCookieValue(request, R0_KEY, "");
            if (url.isEmpty()) {
                QueryWrapper<BgImg> bgImgQueryWrapper = new QueryWrapper<>();
                bgImgQueryWrapper.eq("type", p);
                List<BgImg> bgImgList = bgImgMapper.selectList(bgImgQueryWrapper);
                BgImg bgImg = bgImgList.get(ApiToolController.RANDOM.nextInt(bgImgList.size()));
                url = bgImg.getUrl();
                Cookie cookie = new Cookie(R0_KEY, url);
                cookie.setMaxAge(120 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            response.sendRedirect(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/tong")
    public String tong(HttpServletRequest request, @Nullable @RequestParam("q1") String q1, @Nullable @RequestParam("q2") String q2, @Nullable @RequestParam("u1") String u1, @Nullable @RequestParam("u2") String u2, HttpServletResponse response) {
        String host = request.getHeader("Host");
        try {
            if (Judge.isEmpty(u1) || Judge.isEmpty(u2)) {
                u1 = String.format("https://q1.qlogo.cn/g?b=qq&nk=%s&s=640", q1);
                u2 = String.format("https://q1.qlogo.cn/g?b=qq&nk=%s&s=640", q2);
            }
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(TONG_BASE_BYTES));
            BufferedImage iq1 = ImageIO.read(new URL(u1));
            BufferedImage iq2 = ImageIO.read(new URL(u2));
            int size = 84;
            iq1 = (BufferedImage) ImageDrawerUtils.image2Size(iq1, size, size);
            iq2 = (BufferedImage) ImageDrawerUtils.image2Size(iq2, size + 6, size + 6);
            image = ImageDrawerUtils.putImage(image, iq1, 108, 18);
            image = ImageDrawerUtils.putImage(image, iq2, 16, 10);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            String name = save(baos.toByteArray(), true);
            return String.format(HTTP_FORMAT1, host, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "失败";
    }

    @RequestMapping("/yao2yao")
    public String yao2yao(HttpServletRequest request, @RequestParam("qid") @Nullable String q1,
                          @RequestParam("u1") @Nullable String u1, HttpServletResponse response) throws Exception {
        if (Judge.isEmpty(u1)) {
            u1 = String.format("https://q1.qlogo.cn/g?b=qq&nk=%s&s=640", q1);
        }
        String host = request.getHeader("Host");
        FileWithPath outFile = requestFile(true, "gif");
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outFile.getFile().getAbsolutePath());
        encoder.setRepeat(0);
        encoder.setQuality(5);
        encoder.setFrameRate(200);
        double w = (Math.PI / 12);
        int[] is = new int[]{4, 3, 0, 1, 2};
        final BufferedImage oImage = ImageIO.read(new URL(u1).openStream());
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
        return String.format(HTTP_FORMAT1, host, outFile.getName());
    }

    private static int i0 = -1;

    @NotNull
    private static ImageE0 getIndex(BufferedImage oImage, BufferedImage base, double end, int i) throws IOException {
        int e1 = (int) (end);
        i0 *= -1;
        e1 *= i0;

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

    public static final File[] PA = new File("./files/pa1").listFiles();

    @RequestMapping("/pa")
    public String pa(HttpServletRequest request, @RequestParam("qid") @Nullable String q1, @RequestParam("u1") @Nullable String u1, HttpServletResponse response) throws Exception {
        if (Judge.isEmpty(u1)) {
            u1 = String.format("https://q1.qlogo.cn/g?b=qq&nk=%s&s=640", q1);
        }
        String host = request.getHeader("Host");
        FileWithPath outFile = requestFile(true, "jpg");
        BufferedImage bImage = ImageIO.read(PA[ApiToolController.RANDOM.nextInt(PA.length)]);
        BufferedImage oImage = ImageIO.read(new URL(u1).openStream());
        oImage = (BufferedImage) ImageDrawerUtils.image2Size(oImage, bImage.getWidth() / 6, bImage.getWidth() / 6);
        oImage = (BufferedImage) ImageDrawerUtils.roundImage(oImage, 999);
        bImage = ImageDrawerUtils.putImage(bImage, oImage, 10, bImage.getHeight() - oImage.getHeight() - 10);
        ImageIO.write(bImage, "jpg", outFile.getFile());
        return String.format(HTTP_FORMAT1, host, outFile.getName());
    }

}
