package io.github.kloping.mywebsite.utils;

import io.github.kloping.mywebsite.controller.UtilsController;
import io.github.kloping.mywebsite.entitys.FileWithPath;
import io.github.kloping.mywebsite.entitys.database.GameGoods;
import io.github.kloping.mywebsite.entitys.yuanShen.Avatars;
import io.github.kloping.mywebsite.entitys.yuanShen.Offerings;
import io.github.kloping.mywebsite.entitys.yuanShen.WorldExplorations;
import io.github.kloping.mywebsite.entitys.yuanShen.YuanShenPlayerInfo;
import io.github.kloping.number.NumberUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static io.github.kloping.mywebsite.controller.ApiToolController.RANDOM;

/**
 * @author github.kloping
 */
public class ImageDrawer {
    private static final Font FONT14 = new Font("宋体", Font.BOLD, 14);
    private static final Font FONT16 = new Font("宋体", Font.BOLD, 16);
    private static final Font FONT18 = new Font("宋体", Font.BOLD, 18);
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
        g2d.setColor(PURPLE);
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

    public static final Color PURPLE = new Color(100, 0, 250, 255);
    public static final Color B0 = new Color(144, 201, 193, 255);

    public static String drawerShenInfo(YuanShenPlayerInfo info) throws Exception {
        int width = 600;
        int height = 300;
        height += ((info.getData().getAvatars().length + 2) / 3 * 101);
        height += ((info.getData().getWorld_explorations().length + 1) / 2 * 111);
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.setClip(0, 0, width, height);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.darkGray);

        g2d.setFont(FONT30);
        g2d.drawString("玩家昵称:" + info.getData().getRole().getNickname()
                , 10, 40);

        drawBase0(info, g2d);

        g2d.setColor(Color.BLACK);
        int x = 25, y = 280;
        int xi = 0;
        for (Avatars avatar : info.getData().getAvatars()) {
            drawAvatar(g2d, avatar, x, y);
            x += 175;
            xi++;
            if (xi >= 3) {
                xi = 0;
                x = 25;
                y += 100;
            }
        }
        x = 25;
        xi = 0;
        for (WorldExplorations w : info.getData().getWorld_explorations()) {
            drawWorld(g2d, w, x, y);
            x += 280;
            xi++;
            if (xi >= 2) {
                xi = 0;
                x = 25;
                y += 110;
            }
        }
        FileWithPath fwp = UtilsController.requestFile(true);
        ImageIO.write(outputImage, "jpg", fwp.getFile());
        return fwp.getName();
    }

    private static void drawAvatar(Graphics2D g2d, Avatars avatars, int x, int y) throws IOException {
        g2d.setColor(Color.lightGray);
        g2d.fillRoundRect(x + 5, y - 10, 175, 85, 25, 25);
        g2d.setColor(Color.cyan);
        g2d.drawRoundRect(x + 5, y - 10, 175, 85, 25, 25);
        g2d.setColor(Color.BLACK);

        BufferedImage i0 = ImageIO.read(new URL(avatars.getCard_image()));
        i0 = (BufferedImage) ImageDrawerUtils.image2Size(i0, 70, 70);
        g2d.drawImage(i0, x, y, null);
        x += 75;
        y += 15;
        g2d.setFont(FONT16);
        g2d.drawString(String.format("%s %s命", avatars.getName(), avatars.getActived_constellation_num()), x, y);
        y += 25;
        g2d.drawString(String.format("%s %s星", avatars.getElement(), avatars.getRarity()), x, y);
        y += 25;
        g2d.drawString(String.format("%s级 %s好感", avatars.getLevel(), avatars.getFetter()), x, y);
    }

    private static void drawWorld(Graphics2D g2d, WorldExplorations explorations, int x, int y) throws IOException {
        g2d.setColor(B0);
        g2d.fillRoundRect(x + 5, y - 10, 245, 105, 25, 25);
        g2d.setColor(Color.cyan);
        g2d.drawRoundRect(x + 5, y - 10, 245, 105, 25, 25);
        g2d.setColor(Color.BLACK);

        BufferedImage i0 = ImageIO.read(new URL(explorations.getIcon()));
        i0 = (BufferedImage) ImageDrawerUtils.image2Size(i0, 70, 70);
        g2d.drawImage(i0, x, y, null);
        x += 75;
        y += 15;
        g2d.setFont(FONT18);
        g2d.drawString(explorations.getName(), x, y);
        g2d.setFont(FONT16);
        y += 25;
        g2d.drawString(String.format("探索度:%s%%", NumberUtils.toPercent(explorations.getExploration_percentage(), 1000)), x, y);
        g2d.setFont(FONT14);
        for (Offerings offering : explorations.getOfferings()) {
            y += 20;
            g2d.drawString(String.format("%s: %s级", offering.getName(), offering.getLevel()), x, y);
        }
    }

    private static void drawBase0(YuanShenPlayerInfo info, Graphics2D g2d) {
        g2d.setColor(Color.pink);
        g2d.fillRoundRect(10, 50, 580, 190, 25, 25);
        g2d.setColor(Color.black);

        int x = 25, y = 80;
        int xeve = 10;
        int yeve = 25;
        g2d.setFont(FONT20);
        g2d.drawString("活跃天数", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getActive_day_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=====
        x += 100 - 10;
        g2d.setFont(FONT20);
        g2d.drawString("成就达成数", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getAchievement_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=====
        x += 110;
        g2d.setFont(FONT20);
        g2d.drawString("解锁角色数", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getAvatar_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=====
        x += 110;
        g2d.setFont(FONT20);
        g2d.drawString("解锁传送点数", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getWay_point_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=====
        x += 140;
        g2d.setFont(FONT20);
        g2d.drawString("解锁秘境", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getDomain_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=======================================
        x = 25;
        y = 140;
        g2d.setFont(FONT20);
        g2d.drawString("普通宝箱", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getCommon_chest_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=====
        x += 100;
        g2d.setFont(FONT20);
        g2d.drawString("精致宝箱", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getExquisite_chest_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=====
        x += 100;
        g2d.setFont(FONT20);
        g2d.drawString("珍贵宝箱", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getPrecious_chest_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=====
        x += 100;
        g2d.setFont(FONT20);
        g2d.drawString("华丽宝箱", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getLuxurious_chest_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=====
        x += 100;
        g2d.setFont(FONT20);
        g2d.drawString("奇馈宝箱", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getMagic_chest_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=========================
        x = 25;
        y = 200;
        g2d.setFont(FONT20);
        g2d.drawString("风神瞳", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getAnemoculus_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=====
        x += 100;
        g2d.setFont(FONT20);
        g2d.drawString("岩神瞳", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getGeoculus_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=====
        x += 100;
        g2d.setFont(FONT20);
        g2d.drawString("雷神瞳", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getElectroculus_number().toString(),
                x, y);
        g2d.setColor(Color.black);
        x -= xeve;
        y -= yeve;
        //=====
        x += 100;
        g2d.setFont(FONT20);
        g2d.drawString("深境螺旋", x, y);
        x += xeve;
        y += yeve;
        g2d.setFont(FONT16);
        g2d.setColor(PURPLE);
        g2d.drawString(info.getData().getStats().getSpiral_abyss().toString(),
                x, y);
    }
}
