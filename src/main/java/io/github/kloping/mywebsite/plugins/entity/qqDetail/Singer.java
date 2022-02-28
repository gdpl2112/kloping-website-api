package io.github.kloping.mywebsite.plugins.entity.qqDetail;

public class Singer {
	private String name;
	private String name_hilight;
	private String mid;
	private Number id;

	public String getName(){
		return this.name;
	}

	public Singer setName(String name) {
		this.name = name;
		return this;
	}

	public String getName_hilight(){
		return this.name_hilight;
	}

	public Singer setName_hilight(String name_hilight) {
		this.name_hilight = name_hilight;
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
}