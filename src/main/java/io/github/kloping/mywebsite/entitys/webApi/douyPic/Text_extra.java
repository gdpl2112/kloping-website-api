package io.github.kloping.mywebsite.entitys.webApi.douyPic;

public class Text_extra {
	private Number hashtag_id;
	private Number start;
	private Number end;
	private Number type;
	private String hashtag_name;

	public Number getHashtag_id(){
		return this.hashtag_id;
	}

	public Text_extra setHashtag_id(Number hashtag_id) {
		this.hashtag_id = hashtag_id;
		return this;
	}

	public Number getStart(){
		return this.start;
	}

	public Text_extra setStart(Number start) {
		this.start = start;
		return this;
	}

	public Number getEnd(){
		return this.end;
	}

	public Text_extra setEnd(Number end) {
		this.end = end;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public Text_extra setType(Number type) {
		this.type = type;
		return this;
	}

	public String getHashtag_name(){
		return this.hashtag_name;
	}

	public Text_extra setHashtag_name(String hashtag_name) {
		this.hashtag_name = hashtag_name;
		return this;
	}
}