package io.github.kloping.mywebsite.plugins.entity.douyPic;

public class Video {
	private String vid;
	private Number duration;
	private Cover cover;
	private Boolean has_watermark;
	private Play_addr play_addr;
	private Number width;
	private Origin_cover origin_cover;
	private Number height;
	private String ratio;

	public String getVid(){
		return this.vid;
	}

	public Video setVid(String vid) {
		this.vid = vid;
		return this;
	}

	public Number getDuration(){
		return this.duration;
	}

	public Video setDuration(Number duration) {
		this.duration = duration;
		return this;
	}

	public Cover getCover(){
		return this.cover;
	}

	public Video setCover(Cover cover) {
		this.cover = cover;
		return this;
	}

	public Boolean getHas_watermark(){
		return this.has_watermark;
	}

	public Video setHas_watermark(Boolean has_watermark) {
		this.has_watermark = has_watermark;
		return this;
	}

	public Play_addr getPlay_addr(){
		return this.play_addr;
	}

	public Video setPlay_addr(Play_addr play_addr) {
		this.play_addr = play_addr;
		return this;
	}

	public Number getWidth(){
		return this.width;
	}

	public Video setWidth(Number width) {
		this.width = width;
		return this;
	}

	public Origin_cover getOrigin_cover(){
		return this.origin_cover;
	}

	public Video setOrigin_cover(Origin_cover origin_cover) {
		this.origin_cover = origin_cover;
		return this;
	}

	public Number getHeight(){
		return this.height;
	}

	public Video setHeight(Number height) {
		this.height = height;
		return this;
	}

	public String getRatio(){
		return this.ratio;
	}

	public Video setRatio(String ratio) {
		this.ratio = ratio;
		return this;
	}
}