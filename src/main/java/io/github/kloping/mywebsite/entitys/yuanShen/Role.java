package io.github.kloping.mywebsite.entitys.yuanShen;

/**
 * @author HRS-Computer
 */
public class Role {
	private String AvatarUrl;
	private Number level;
	private String nickname;
	private String region;

	public String getAvatarUrl(){
		return this.AvatarUrl;
	}

	public Role setAvatarUrl(String AvatarUrl) {
		this.AvatarUrl = AvatarUrl;
		return this;
	}

	public Number getLevel(){
		return this.level;
	}

	public Role setLevel(Number level) {
		this.level = level;
		return this;
	}

	public String getNickname(){
		return this.nickname;
	}

	public Role setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public String getRegion(){
		return this.region;
	}

	public Role setRegion(String region) {
		this.region = region;
		return this;
	}
}