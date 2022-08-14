package io.github.kloping.mywebsite.entitys.webApi.fcgPlaySingleSong;

public class Mv {
	private String vid;
	private String name;
	private Number id;
	private String title;
	private Number vt;

	public String getVid(){
		return this.vid;
	}

	public Mv setVid(String vid) {
		this.vid = vid;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Mv setName(String name) {
		this.name = name;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Mv setId(Number id) {
		this.id = id;
		return this;
	}

	public String getTitle(){
		return this.title;
	}

	public Mv setTitle(String title) {
		this.title = title;
		return this;
	}

	public Number getVt(){
		return this.vt;
	}

	public Mv setVt(Number vt) {
		this.vt = vt;
		return this;
	}
}