package io.github.kloping.mywebsite.entitys.webApi.fcgPlaySingleSong;

public class Ksong {
	private String mid;
	private Number id;

	public String getMid(){
		return this.mid;
	}

	public Ksong setMid(String mid) {
		this.mid = mid;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Ksong setId(Number id) {
		this.id = id;
		return this;
	}
}