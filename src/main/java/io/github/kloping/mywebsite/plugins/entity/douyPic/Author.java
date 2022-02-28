package io.github.kloping.mywebsite.plugins.entity.douyPic;

public class Author {
	private Avatar_medium avatar_medium;
	private String unique_id;
	private String signature;
	private Avatar_thumb avatar_thumb;
	private String short_id;
	private Avatar_larger avatar_larger;
	private String uid;
	private String nickname;

	public String getUid(){
		return this.uid;
	}

	public Author setUid(String uid) {
		this.uid = uid;
		return this;
	}

	public Avatar_medium getAvatar_medium(){
		return this.avatar_medium;
	}

	public Author setAvatar_medium(Avatar_medium avatar_medium) {
		this.avatar_medium = avatar_medium;
		return this;
	}

	public String getUnique_id(){
		return this.unique_id;
	}

	public Author setUnique_id(String unique_id) {
		this.unique_id = unique_id;
		return this;
	}

	public String getSignature(){
		return this.signature;
	}

	public Author setSignature(String signature) {
		this.signature = signature;
		return this;
	}

	public Avatar_thumb getAvatar_thumb(){
		return this.avatar_thumb;
	}

	public Author setAvatar_thumb(Avatar_thumb avatar_thumb) {
		this.avatar_thumb = avatar_thumb;
		return this;
	}

	public String getNickname(){
		return this.nickname;
	}

	public Author setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public String getShort_id(){
		return this.short_id;
	}

	public Author setShort_id(String short_id) {
		this.short_id = short_id;
		return this;
	}

	public Avatar_larger getAvatar_larger(){
		return this.avatar_larger;
	}

	public Author setAvatar_larger(Avatar_larger avatar_larger) {
		this.avatar_larger = avatar_larger;
		return this;
	}
}