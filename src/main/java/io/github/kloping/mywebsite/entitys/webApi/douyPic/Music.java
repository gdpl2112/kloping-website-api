package io.github.kloping.mywebsite.entitys.webApi.douyPic;

public class Music {
	private Number duration;
	private Play_url play_url;
	private String author;
	private Cover_medium cover_medium;
	private String mid;
	private Number id;
	private Cover_hd cover_hd;
	private String title;
	private Cover_large cover_large;
	private Cover_thumb cover_thumb;
	private Number status;

	public Number getDuration(){
		return this.duration;
	}

	public Music setDuration(Number duration) {
		this.duration = duration;
		return this;
	}

	public Play_url getPlay_url(){
		return this.play_url;
	}

	public Music setPlay_url(Play_url play_url) {
		this.play_url = play_url;
		return this;
	}

	public String getAuthor(){
		return this.author;
	}

	public Music setAuthor(String author) {
		this.author = author;
		return this;
	}

	public Cover_medium getCover_medium(){
		return this.cover_medium;
	}

	public Music setCover_medium(Cover_medium cover_medium) {
		this.cover_medium = cover_medium;
		return this;
	}

	public String getMid(){
		return this.mid;
	}

	public Music setMid(String mid) {
		this.mid = mid;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Music setId(Number id) {
		this.id = id;
		return this;
	}

	public Cover_hd getCover_hd(){
		return this.cover_hd;
	}

	public Music setCover_hd(Cover_hd cover_hd) {
		this.cover_hd = cover_hd;
		return this;
	}

	public String getTitle(){
		return this.title;
	}

	public Music setTitle(String title) {
		this.title = title;
		return this;
	}

	public Cover_large getCover_large(){
		return this.cover_large;
	}

	public Music setCover_large(Cover_large cover_large) {
		this.cover_large = cover_large;
		return this;
	}

	public Cover_thumb getCover_thumb(){
		return this.cover_thumb;
	}

	public Music setCover_thumb(Cover_thumb cover_thumb) {
		this.cover_thumb = cover_thumb;
		return this;
	}

	public Number getStatus(){
		return this.status;
	}

	public Music setStatus(Number status) {
		this.status = status;
		return this;
	}
}