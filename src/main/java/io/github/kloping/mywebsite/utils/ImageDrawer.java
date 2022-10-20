package io.github.kloping.mywebsite.utils;

import io.github.kloping.mywebsite.controller.UtilsController;
import io.github.kloping.mywebsite.entitys.FileWithPath;
import io.github.kloping.mywebsite.entitys.database.GameGoods;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static io.github.kloping.mywebsite.controller.ApiToolController.RANDOM;

/**
 * @author github.kloping
 */
public class ImageDrawer {
    private static final Font FONT16 = new Font("宋体", Font.BOLD, 16);
    private static final Font FONT20 = new Font("宋体", Font.BOLD, 20);
    private static final Font FONT30 = new Font("宋体", Font.BOLD, 30);
    private static final Color C0 = new Color(250, 253, 255);

    public static String drawer(String desc, List<GameGoods> goods, String end) throws Exception {
        int width = 600;
        int height = 500;
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.setClip(0, 0, width, height);
        g2d.setColor(C0);
        g2d.fillRect(0, 0, width, height);
        g2d.setFont(FONT20);
        g2d.setColor(Color.RED);
        g2d.drawString(desc, 40, 60);
        g2d.setFont(FONT16);
        g2d.setColor(Color.BLACK);
        int x = 30, y = 130;
        int i = -1;
        for (GameGoods good : goods) {
            i++;
            if (i % 2 == 0) {
                x = 30;
                y += 50;
            } else {
                x += 250;
            }
            g2d.drawString(String.format("| %s 价格:%s 数量:%s |", good.getName(), good.getPrice1(), good.getNum()), x, y);
        }
        g2d.setFont(FONT20);
        g2d.setColor(Color.CYAN);
        g2d.drawString(end, 20, 430);
        g2d.dispose();
        FileWithPath fwp = UtilsController.requestFile(true);
        ImageIO.write(outputImage, "jpg", fwp.getFile());
        return fwp.getName();
    }


    public static String createImage(String... sss) throws IOException {
        int width = 500;
        int height = (sss.length + 3) * 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, width, height);
        g.setFont(FONT30);
        Rectangle clip = g.getClipBounds();
        FontMetrics fm = g.getFontMetrics(FONT30);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        g.setColor(Color.BLACK);
        g.drawString("※====☆=?==★===?====$==*=※", 10, 40);
        for (int i = 0; i < sss.length; i++) {
            g.setColor(COLORS[RANDOM.nextInt(COLORS.length)]);
            g.drawString("◎" + sss[i], 10, (i + 2) * 40);
        }
        g.setColor(Color.BLACK);
        g.drawString("※====☆=?==★===?====$==*=※", 10, (sss.length + 2) * 40);
        g.dispose();
        FileWithPath fwp = UtilsController.requestFile(true);
        ImageIO.write(image, "jpg", fwp.getFile());
        return fwp.getName();
    }

    private static final Color[] COLORS = new Color[]{
            Color.BLUE,
            Color.RED,
            Color.RED,
            new Color(159, 4, 180),
            new Color(180, 66, 4),
            Color.DARK_GRAY,
            Color.BLACK
    };
}
