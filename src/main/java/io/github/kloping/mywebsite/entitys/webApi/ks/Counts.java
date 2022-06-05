package io.github.kloping.mywebsite.entitys.webApi.ks;

public class Counts {
	private Number fanCount;
	private Number followCount;
	private Number photoCount;

	public Number getFanCount(){
		return this.fanCount;
	}

	public Counts setFanCount(Number fanCount) {
		this.fanCount = fanCount;
		return this;
	}

	public Number getFollowCount(){
		return this.followCount;
	}

	public Counts setFollowCount(Number followCount) {
		this.followCount = followCount;
		return this;
	}

	public Number getPhotoCount(){
		return this.photoCount;
	}

	public Counts setPhotoCount(Number photoCount) {
		this.photoCount = photoCount;
		return this;
	}
}