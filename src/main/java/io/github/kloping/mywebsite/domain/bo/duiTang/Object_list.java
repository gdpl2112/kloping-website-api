package io.github.kloping.mywebsite.domain.bo.duiTang;

public class Object_list {
	private String msg;
	private Number buyable;
	private Boolean is_certify_user;
	private Number like_id;
	private String add_datetime_pretty;
	private Photo photo;
	private Number sender_id;
	private Number oriAddDatetime;
	private Number add_datetime_ts;
	private Number favorite_count;
	private String extra_type;
	private Number id;
	private Boolean short_video;
	private String add_datetime;

	public String getMsg(){
		return this.msg;
	}

	public Object_list setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Number getBuyable(){
		return this.buyable;
	}

	public Object_list setBuyable(Number buyable) {
		this.buyable = buyable;
		return this;
	}

	public Boolean getIs_certify_user(){
		return this.is_certify_user;
	}

	public Object_list setIs_certify_user(Boolean is_certify_user) {
		this.is_certify_user = is_certify_user;
		return this;
	}

	public Number getLike_id(){
		return this.like_id;
	}

	public Object_list setLike_id(Number like_id) {
		this.like_id = like_id;
		return this;
	}

	public String getAdd_datetime_pretty(){
		return this.add_datetime_pretty;
	}

	public Object_list setAdd_datetime_pretty(String add_datetime_pretty) {
		this.add_datetime_pretty = add_datetime_pretty;
		return this;
	}

	public Photo getPhoto(){
		return this.photo;
	}

	public Object_list setPhoto(Photo photo) {
		this.photo = photo;
		return this;
	}

	public Number getSender_id(){
		return this.sender_id;
	}

	public Object_list setSender_id(Number sender_id) {
		this.sender_id = sender_id;
		return this;
	}

	public Number getOriAddDatetime(){
		return this.oriAddDatetime;
	}

	public Object_list setOriAddDatetime(Number oriAddDatetime) {
		this.oriAddDatetime = oriAddDatetime;
		return this;
	}

	public Number getAdd_datetime_ts(){
		return this.add_datetime_ts;
	}

	public Object_list setAdd_datetime_ts(Number add_datetime_ts) {
		this.add_datetime_ts = add_datetime_ts;
		return this;
	}

	public Number getFavorite_count(){
		return this.favorite_count;
	}

	public Object_list setFavorite_count(Number favorite_count) {
		this.favorite_count = favorite_count;
		return this;
	}

	public String getExtra_type(){
		return this.extra_type;
	}

	public Object_list setExtra_type(String extra_type) {
		this.extra_type = extra_type;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Object_list setId(Number id) {
		this.id = id;
		return this;
	}

	public Boolean getShort_video(){
		return this.short_video;
	}

	public Object_list setShort_video(Boolean short_video) {
		this.short_video = short_video;
		return this;
	}

	public String getAdd_datetime(){
		return this.add_datetime;
	}

	public Object_list setAdd_datetime(String add_datetime) {
		this.add_datetime = add_datetime;
		return this;
	}
}