package io.github.kloping.mywebsite.entitys.douyin;

public class Author {
	private String uid;
	private Number secret;
	private Boolean isBlockedV2;
	private Number userNotSee;

	public String getUid(){
		return this.uid;
	}

	public Author setUid(String uid) {
		this.uid = uid;
		return this;
	}

	public Number getSecret(){
		return this.secret;
	}

	public Author setSecret(Number secret) {
		this.secret = secret;
		return this;
	}

	public Boolean getIsBlockedV2(){
		return this.isBlockedV2;
	}

	public Author setIsBlockedV2(Boolean isBlockedV2) {
		this.isBlockedV2 = isBlockedV2;
		return this;
	}

	public Number getUserNotSee(){
		return this.userNotSee;
	}

	public Author setUserNotSee(Number userNotSee) {
		this.userNotSee = userNotSee;
		return this;
	}
}