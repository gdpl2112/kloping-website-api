package io.github.kloping.mywebsite.entitys.webApi.ks;

public class TagShow {
	private Number bannerType;
	private String bizId;
	private Number type;
	private String usedCount;

	public Number getBannerType(){
		return this.bannerType;
	}

	public TagShow setBannerType(Number bannerType) {
		this.bannerType = bannerType;
		return this;
	}

	public String getBizId(){
		return this.bizId;
	}

	public TagShow setBizId(String bizId) {
		this.bizId = bizId;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public TagShow setType(Number type) {
		this.type = type;
		return this;
	}

	public String getUsedCount(){
		return this.usedCount;
	}

	public TagShow setUsedCount(String usedCount) {
		this.usedCount = usedCount;
		return this;
	}
}