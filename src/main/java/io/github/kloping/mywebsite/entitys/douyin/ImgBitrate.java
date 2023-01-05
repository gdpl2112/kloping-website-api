package io.github.kloping.mywebsite.entitys.douyin;

public class ImgBitrate {
	private Images[] images;
	private String name;

	public Images[] getImages(){
		return this.images;
	}

	public ImgBitrate setImages(Images[] images) {
		this.images = images;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public ImgBitrate setName(String name) {
		this.name = name;
		return this;
	}
}