package io.github.kloping.mywebsite.plugins.entity.kugouSong;

public class Authors {
	private String author_name;
	private String avatar;
	private String author_id;
	private String is_publish;
	private String sizable_avatar;

	public String getAuthor_name(){
		return this.author_name;
	}

	public Authors setAuthor_name(String author_name) {
		this.author_name = author_name;
		return this;
	}

	public String getAvatar(){
		return this.avatar;
	}

	public Authors setAvatar(String avatar) {
		this.avatar = avatar;
		return this;
	}

	public String getAuthor_id(){
		return this.author_id;
	}

	public Authors setAuthor_id(String author_id) {
		this.author_id = author_id;
		return this;
	}

	public String getIs_publish(){
		return this.is_publish;
	}

	public Authors setIs_publish(String is_publish) {
		this.is_publish = is_publish;
		return this;
	}

	public String getSizable_avatar(){
		return this.sizable_avatar;
	}

	public Authors setSizable_avatar(String sizable_avatar) {
		this.sizable_avatar = sizable_avatar;
		return this;
	}
}