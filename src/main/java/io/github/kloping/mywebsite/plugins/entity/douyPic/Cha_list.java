package io.github.kloping.mywebsite.plugins.entity.douyPic;

public class Cha_list {
	private Number user_count;
	private Boolean is_commerce;
	private String hash_tag_profile;
	private Number type;
	private Cover_item cover_item;
	private Number view_count;
	private String cha_name;
	private String desc;
	private String cid;

	public Number getUser_count(){
		return this.user_count;
	}

	public Cha_list setUser_count(Number user_count) {
		this.user_count = user_count;
		return this;
	}

	public Boolean getIs_commerce(){
		return this.is_commerce;
	}

	public Cha_list setIs_commerce(Boolean is_commerce) {
		this.is_commerce = is_commerce;
		return this;
	}

	public String getHash_tag_profile(){
		return this.hash_tag_profile;
	}

	public Cha_list setHash_tag_profile(String hash_tag_profile) {
		this.hash_tag_profile = hash_tag_profile;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public Cha_list setType(Number type) {
		this.type = type;
		return this;
	}

	public Cover_item getCover_item(){
		return this.cover_item;
	}

	public Cha_list setCover_item(Cover_item cover_item) {
		this.cover_item = cover_item;
		return this;
	}

	public Number getView_count(){
		return this.view_count;
	}

	public Cha_list setView_count(Number view_count) {
		this.view_count = view_count;
		return this;
	}

	public String getCha_name(){
		return this.cha_name;
	}

	public Cha_list setCha_name(String cha_name) {
		this.cha_name = cha_name;
		return this;
	}

	public String getDesc(){
		return this.desc;
	}

	public Cha_list setDesc(String desc) {
		this.desc = desc;
		return this;
	}

	public String getCid(){
		return this.cid;
	}

	public Cha_list setCid(String cid) {
		this.cid = cid;
		return this;
	}
}