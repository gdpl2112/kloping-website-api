package io.github.kloping.mywebsite.domain.bo.fcgPlaySingleSong;

public class Singer {
	private String name;
	private String mid;
	private Number id;
	private Number uin;
	private String pmid;
	private String title;
	private Number type;

	public String getName(){
		return this.name;
	}

	public Singer setName(String name) {
		this.name = name;
		return this;
	}

	public String getMid(){
		return this.mid;
	}

	public Singer setMid(String mid) {
		this.mid = mid;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Singer setId(Number id) {
		this.id = id;
		return this;
	}

	public Number getUin(){
		return this.uin;
	}

	public Singer setUin(Number uin) {
		this.uin = uin;
		return this;
	}

	public String getPmid(){
		return this.pmid;
	}

	public Singer setPmid(String pmid) {
		this.pmid = pmid;
		return this;
	}

	public String getTitle(){
		return this.title;
	}

	public Singer setTitle(String title) {
		this.title = title;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public Singer setType(Number type) {
		this.type = type;
		return this;
	}
}