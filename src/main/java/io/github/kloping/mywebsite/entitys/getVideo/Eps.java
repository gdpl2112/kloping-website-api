package io.github.kloping.mywebsite.entitys.getVideo;

public class Eps {
	private String name;
	private String url;

	public String getName(){
		return this.name;
	}

	public Eps setName(String name) {
		this.name = name;
		return this;
	}

	public String getUrl(){
		return this.url;
	}

	public Eps setUrl(String url) {
		this.url = url;
		return this;
	}
}