package io.github.kloping.mywebsite.entitys.douyin;

/**
 * @author HRS-Computer
 */
public class DouYinPicData {
    private One one;
    private Thirty thirty;
    private String location;

    public Thirty getThirty() {
        return this.thirty;
    }

    public DouYinPicData setThirty(Thirty Thirty) {
        this.thirty = Thirty;
        return this;
    }

    public String getLocation() {
        return this.location;
    }

    public DouYinPicData setLocation(String location) {
        this.location = location;
        return this;
    }

    public One getOne() {
        return this.one;
    }

    public DouYinPicData setOne(One One) {
        this.one = One;
        return this;
    }
}