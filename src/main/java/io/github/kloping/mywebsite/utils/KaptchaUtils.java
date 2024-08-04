package io.github.kloping.mywebsite.utils;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.github.kloping.mywebsite.KlopingBlogApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static io.github.kloping.mywebsite.controller.api.ApiToolController.RANDOM;

/**
 * @author github.kloping
 */
public class KaptchaUtils {

    public static char[] cs = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static DefaultKaptcha defaultKaptcha;

    public synchronized static DefaultKaptcha getDefaultKaptcha() {
        if (defaultKaptcha == null) {
            defaultKaptcha = KlopingBlogApplication.applicationContext.getBean(DefaultKaptcha.class);
        }
        return defaultKaptcha;
    }

    public static String getCode() {
        char[] chars = new char[4];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = cs[RANDOM.nextInt(cs.length)];
        }
        String caps = new String(chars);
        return caps;
    }

    public static String getNumberCode(int w) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < w; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }

    public static String getNumberCode() {
        return getNumberCode(4);
    }

    /**
     * abs path
     * code
     * name
     *
     * @return
     */
    public static Object[] createCapImage() {
        try {
            String caps = getCode();
            BufferedImage bi = getDefaultKaptcha().createImage(caps);
            File file = new File("./temp/" + UUID.randomUUID() + ".png");
            ImageIO.write(bi, "png", file);
            return new Object[]{file.getAbsolutePath(), caps, file.getName()};
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[]{e.getMessage()};
        }
    }
}
