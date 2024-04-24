package io.github.kloping.mywebsite.domain.bo;

import java.awt.image.BufferedImage;

/**
 * @author HRS-Computer
 */
public final class ImageE0 {
    private BufferedImage image;
    private Integer x;
    private Integer y;

    public ImageE0(BufferedImage image, Integer x, Integer y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
