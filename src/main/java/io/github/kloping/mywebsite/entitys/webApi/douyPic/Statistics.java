package io.github.kloping.mywebsite.entitys.webApi.douyPic;

public class Statistics {
	private Number share_count;
	private Number comment_count;
	private String aweme_id;
	private Number digg_count;
	private Number play_count;

	public Number getShare_count(){
		return this.share_count;
	}

	public Statistics setShare_count(Number share_count) {
		this.share_count = share_count;
		return this;
	}

	public Number getComment_count(){
		return this.comment_count;
	}

	public Statistics setComment_count(Number comment_count) {
		this.comment_count = comment_count;
		return this;
	}

	public String getAweme_id(){
		return this.aweme_id;
	}

	public Statistics setAweme_id(String aweme_id) {
		this.aweme_id = aweme_id;
		return this;
	}

	public Number getDigg_count(){
		return this.digg_count;
	}

	public Statistics setDigg_count(Number digg_count) {
		this.digg_count = digg_count;
		return this;
	}

	public Number getPlay_count(){
		return this.play_count;
	}

	public Statistics setPlay_count(Number play_count) {
		this.play_count = play_count;
		return this;
	}
}