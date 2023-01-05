package io.github.kloping.mywebsite.entitys.douyin;

public class Stats {
	private Number shareCount;
	private Number playCount;
	private Number collectCount;
	private Number diggCount;
	private Number commentCount;

	public Number getShareCount(){
		return this.shareCount;
	}

	public Stats setShareCount(Number shareCount) {
		this.shareCount = shareCount;
		return this;
	}

	public Number getPlayCount(){
		return this.playCount;
	}

	public Stats setPlayCount(Number playCount) {
		this.playCount = playCount;
		return this;
	}

	public Number getCollectCount(){
		return this.collectCount;
	}

	public Stats setCollectCount(Number collectCount) {
		this.collectCount = collectCount;
		return this;
	}

	public Number getDiggCount(){
		return this.diggCount;
	}

	public Stats setDiggCount(Number diggCount) {
		this.diggCount = diggCount;
		return this;
	}

	public Number getCommentCount(){
		return this.commentCount;
	}

	public Stats setCommentCount(Number commentCount) {
		this.commentCount = commentCount;
		return this;
	}
}