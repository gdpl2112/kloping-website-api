package io.github.kloping.mywebsite.utils;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import io.github.kloping.file.FileUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author github-kloping
 */
public class ImageDrawerUtils {

    public static final byte[] TONG_BASE_BYTES = FileUtils.getBytesFromFile("./files/tong-base.jpg");

    /**
     * 图片圆角
     *
     * @param image        图片
     * @param cornerRadius 幅度
     * @return
     */
    public static BufferedImage roundImage(BufferedImage image, int cornerRadius) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = outputImage.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return outputImage;
    }

    /**
     * 压缩指定宽、高
     *
     * @param bimg
     * @param width
     * @param height
     * @param tagFilePath
     * @return
     */
    public static Image image2Size(BufferedImage bimg, int width, int height) throws IOException {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp1", ".png");
            Thumbnails.of(bimg).size(width, height).outputQuality(1F).toFile(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage image = ImageIO.read(tempFile);
        tempFile.delete();
        return image;
    }

    /**
     * 旋转图片
     *
     * @param image
     * @param rotate
     * @return
     * @throws IOException
     */
    public static Image rotateImage(BufferedImage image, float rotate) throws IOException {
        File tempFile = null;
        try {
            int _w = image.getWidth();
            int _h = image.getHeight();
            tempFile = File.createTempFile("temp2", ".png");
            Thumbnails.of(image).scale(1F).rotate(rotate).toFile(tempFile);
            BufferedImage i1 = ImageIO.read(tempFile);
            Thumbnails.of(i1)
                    .sourceRegion(Positions.CENTER, _w, _h)
                    .size(_w, _h)
                    .keepAspectRatio(false)
                    .toFile(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = ImageIO.read(tempFile);
        tempFile.delete();
        return image;
    }

    /**
     * 将 一张图片放到 一张图片上
     *
     * @param image
     * @param im
     * @param x
     * @param y
     * @return
     */
    public static BufferedImage putImage(BufferedImage image, BufferedImage im, int x, int y) {
        Graphics graphics = image.getGraphics();
        graphics.drawImage(im, x, y, null);
        graphics.dispose();
        return image;
    }

    /**
     * 画一条线
     *
     * @param image
     * @param x
     * @param y
     * @return
     */
    public static BufferedImage putRect(BufferedImage image, int x, int y, int length, int height) {
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x, y, length, height);
        graphics.dispose();
        return image;
    }

    public static Image getImageByUrl2Size(URL url, int width, int height) {
        try {
            BufferedImage image = ImageIO.read(url);
            return image2Size(image, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image getImageByColor2Size(Color color, int width, int height) {
        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics2D g2d = image.createGraphics();
            g2d.setClip(0, 0, width, height);
            g2d.setColor(color);
            g2d.fillRect(0, 0, width, height);
            g2d.dispose();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 在 图片 上 绕指定点 旋转 图片
     *
     * @param image
     * @param o
     * @param rotate
     * @param x
     * @param y
     * @return
     * @throws IOException
     */
    public static Image rotateImage(BufferedImage image, BufferedImage o, float rotate, int x, int y) throws IOException {

        return image;
    }

    public static String image2gift(int delay, File outFile, String... images) {
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outFile.getAbsolutePath());
        encoder.setRepeat(0);
        encoder.setQuality(5);
        encoder.setFrameRate(delay);
        for (int i = 0; i < images.length; i++) {
            String u0 = images[i];
            encoder.setDelay(delay);
            BufferedImage main = null;
            try {
                if (u0 == null) continue;
                else if (u0.startsWith("http")) {
                    main = ImageIO.read(new URL(u0));
                } else {
                    main = ImageIO.read(new File(u0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            encoder.addFrame(main);
        }
        encoder.finish();
        return outFile.getAbsolutePath();
    }

    public static BufferedImage changeAlpha(BufferedImage image, int alpha) throws Exception {
        File tempFile1 = null;
        File tempFile2 = null;
        try {
            tempFile1 = File.createTempFile("temp1", ".jpg");
            tempFile2 = File.createTempFile("temp2", ".jpg");
            ImageIO.write(image, "jpg", tempFile1);
            changeAlpha(tempFile1.getAbsolutePath(), tempFile2.getAbsolutePath(), alpha);
            return ImageIO.read(tempFile2);
        } finally {
            tempFile1.delete();
            tempFile2.delete();
        }
    }

    /**
     * @param path    源路径
     * @param tarPath 生成路径
     * @param alpha   透明度   （0不透明---10全透明）
     */
    public static void changeAlpha(String path, String tarPath, int alpha) {
        //检查透明度是否越界
        if (alpha < 0) {
            alpha = 0;
        } else if (alpha > 10) {
            alpha = 10;
        }
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int weight = image.getWidth();
            int height = image.getHeight();

            BufferedImage output = new BufferedImage(weight, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = output.createGraphics();
            output = g2.getDeviceConfiguration().createCompatibleImage(weight, height, Transparency.TRANSLUCENT);
            g2.dispose();
            g2 = output.createGraphics();

            //调制透明度
            for (int j1 = output.getMinY(); j1 < output.getHeight(); j1++) {
                for (int j2 = output.getMinX(); j2 < output.getWidth(); j2++) {
                    int rgb = output.getRGB(j2, j1);
                    rgb = ((alpha * 255 / 10) << 24) | (rgb & 0x00ffffff);
                    output.setRGB(j2, j1, rgb);
                }
            }
            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(image, 0, 0, weight, height, null);
            g2.dispose();
            ImageIO.write(output, "png", new File(tarPath));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
