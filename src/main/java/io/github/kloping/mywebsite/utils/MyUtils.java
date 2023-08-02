package io.github.kloping.mywebsite.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author github-kloping
 */
public class MyUtils {
    /**
     * 获取cookie
     *
     * @param request
     * @param key     cookie value
     * @param value   默认值
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String key, String value) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return value;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }
        return value;
    }

    /**
     * @param bufferedImage 图片
     * @param angel         旋转角度
     * @return
     */
    public static BufferedImage rotateImage(BufferedImage bufferedImage, int angel) {
        if (bufferedImage == null) {
            return null;
        }
        if (angel < 0) {

            angel = angel + 360;
        }
        int imageWidth = bufferedImage.getWidth(null);
        int imageHeight = bufferedImage.getHeight(null);
        Rectangle rectangle = calculatorRotatedSize(new Rectangle(new Dimension(imageWidth, imageHeight)), angel);
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage newImage = null;
        newImage = new BufferedImage(rectangle.width, rectangle.height, type);
        Graphics2D graphics = newImage.createGraphics();
        graphics.translate((rectangle.width - imageWidth) / 2, (rectangle.height - imageHeight) / 2);
        graphics.rotate(Math.toRadians(angel), imageWidth / 2, imageHeight / 2);
        graphics.drawImage(bufferedImage, null, null);
        return newImage;
    }

    /**
     * 计算旋转后的尺寸
     *
     * @param src
     * @param angel
     * @return
     */
    private static Rectangle calculatorRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }
        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);
        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new java.awt.Rectangle(new Dimension(des_width, des_height));
    }

    /**
     * 获取网络图片流（附加）
     *
     * @param url
     * @return
     */
    public static InputStream getImageStream(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return inputStream;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(20);

    public static String toUrlString(String s, String charset) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes(charset);
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 压缩图片
     *
     * @param file
     * @param qality 参数qality是取值0~1范围内  代表压缩的程度
     * @return
     * @throws IOException
     */
    public static File compressPictureByQality(File file, float qality) throws IOException {
        BufferedImage src = null;
        FileOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
        imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imgWriteParams.setCompressionQuality(qality);
        imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
        ColorModel colorModel = ImageIO.read(file).getColorModel();
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
                colorModel, colorModel.createCompatibleSampleModel(32, 32)));
        if (!file.exists()) {
            throw new FileNotFoundException("Not Found Img File,文件不存在");
        } else {
            src = ImageIO.read(file);
            out = new FileOutputStream(file);
            imgWrier.reset();
            imgWrier.setOutput(ImageIO.createImageOutputStream(out));
            imgWrier.write(null, new IIOImage(src, null, null),
                    imgWriteParams);
            out.flush();
            out.close();
            return file;
        }
    }

    public static String unescape(String s) {
        StringBuffer sbuf = new StringBuffer();
        int l = s.length();
        int ch = -1;
        int b, sumb = 0;
        for (int i = 0, more = -1; i < l; i++) {
            switch (ch = s.charAt(i)) {
                case '%':
                    ch = s.charAt(++i);
                    int hb = (Character.isDigit((char) ch) ? ch - '0' : 10 + Character.toLowerCase((char) ch) - 'a') & 0xF;
                    ch = s.charAt(++i);
                    int lb = (Character.isDigit((char) ch) ? ch - '0' : 10 + Character.toLowerCase((char) ch) - 'a') & 0xF;
                    b = (hb << 4) | lb;
                    break;
                case '+':
                    b = ' ';
                    break;
                default:
                    b = ch;
            }
            if ((b & 0xc0) == 0x80) {
                sumb = (sumb << 6) | (b & 0x3f);
                if (--more == 0)
                    sbuf.append((char) sumb);
            } else if ((b & 0x80) == 0x00) {
                sbuf.append((char) b);
            } else if ((b & 0xe0) == 0xc0) {
                sumb = b & 0x1f;
                more = 1;
            } else if ((b & 0xf0) == 0xe0) {
                sumb = b & 0x0f;
                more = 2;
            } else if ((b & 0xf8) == 0xf0) {
                sumb = b & 0x07;
                more = 3;
            } else if ((b & 0xfc) == 0xf8) {
                sumb = b & 0x03;
                more = 4;
            } else {
                sumb = b & 0x01;
                more = 5;
            }
        }
        return sbuf.toString();
    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public static byte[] post(String url, Map<String, String> km) throws IOException {
        return Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true)
                .data(km).userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36 Edg/113.0.1774.35"
                ).method(Connection.Method.POST).execute().bodyAsBytes();
    }

    public static String getTimeFormat0(long l) {
        int hour = 0;
        int min = 0;
        int sec = 0;
        while (l > 3600000) {
            hour++;
            l -= 3600000;
        }
        while (l > 1000) {
            sec++;
            if (sec == 60) {
                min++;
                sec = 0;
            }
            l -= 1000;
        }
        return String.format("%s小时%s分钟%s秒", hour, min, sec);
    }
}
