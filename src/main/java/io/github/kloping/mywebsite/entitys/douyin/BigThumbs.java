package io.github.kloping.mywebsite.entitys.douyin;

public class BigThumbs {
	private Number duration;
	private String fext;
	private String img_url;
	private Number img_x_len;
	private Number img_x_size;
	private Number img_y_size;
	private Number img_y_len;
	private Number interval;
	private Number img_num;
	private String uri;

	public Number getDuration(){
		return this.duration;
	}

	public BigThumbs setDuration(Number duration) {
		this.duration = duration;
		return this;
	}

	public String getFext(){
		return this.fext;
	}

	public BigThumbs setFext(String fext) {
		this.fext = fext;
		return this;
	}

	public String getImg_url(){
		return this.img_url;
	}

	public BigThumbs setImg_url(String img_url) {
		this.img_url = img_url;
		return this;
	}

	public Number getImg_x_len(){
		return this.img_x_len;
	}

	public BigThumbs setImg_x_len(Number img_x_len) {
		this.img_x_len = img_x_len;
		return this;
	}

	public Number getImg_x_size(){
		return this.img_x_size;
	}

	public BigThumbs setImg_x_size(Number img_x_size) {
		this.img_x_size = img_x_size;
		return this;
	}

	public Number getImg_y_size(){
		return this.img_y_size;
	}

	public BigThumbs setImg_y_size(Number img_y_size) {
		this.img_y_size = img_y_size;
		return this;
	}

	public Number getImg_y_len(){
		return this.img_y_len;
	}

	public BigThumbs setImg_y_len(Number img_y_len) {
		this.img_y_len = img_y_len;
		return this;
	}

	public Number getInterval(){
		return this.interval;
	}

	public BigThumbs setInterval(Number interval) {
		this.interval = interval;
		return this;
	}

	public Number getImg_num(){
		return this.img_num;
	}

	public BigThumbs setImg_num(Number img_num) {
		this.img_num = img_num;
		return this;
	}

	public String getUri(){
		return this.uri;
	}

	public BigThumbs setUri(String uri) {
		this.uri = uri;
		return this;
	}
}