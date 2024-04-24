package io.github.kloping.mywebsite.domain.bo.fcgPlaySingleSong;

public class Album {
	private String time_public;
	private String subtitle;
	private String name;
	private String mid;
	private Number id;
	private String pmid;
	private String title;

	public String getTime_public(){
		return this.time_public;
	}

	public Album setTime_public(String time_public) {
		this.time_public = time_public;
		return this;
	}

	public String getSubtitle(){
		return this.subtitle;
	}

	public Album setSubtitle(String subtitle) {
		this.subtitle = subtitle;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Album setName(String name) {
		this.name = name;
		return this;
	}

	public String getMid(){
		return this.mid;
	}

	public Album setMid(String mid) {
		this.mid = mid;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Album setId(Number id) {
		this.id = id;
		return this;
	}

	public String getPmid(){
		return this.pmid;
	}

	public Album setPmid(String pmid) {
		this.pmid = pmid;
		return this;
	}

	public String getTitle(){
		return this.title;
	}

	public Album setTitle(String title) {
		this.title = title;
		return this;
	}
}