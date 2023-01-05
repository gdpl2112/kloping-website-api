package io.github.kloping.mywebsite.entitys.douyin;

public class PlayUrl {
	private Number width;
	private String[] urlList;
	private String uri;
	private Number height;
	private String urlKey;

	public Number getWidth(){
		return this.width;
	}

	public PlayUrl setWidth(Number width) {
		this.width = width;
		return this;
	}

	public String[] getUrlList(){
		return this.urlList;
	}

	public PlayUrl setUrlList(String[] urlList) {
		this.urlList = urlList;
		return this;
	}

	public String getUri(){
		return this.uri;
	}

	public PlayUrl setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public Number getHeight(){
		return this.height;
	}

	public PlayUrl setHeight(Number height) {
		this.height = height;
		return this;
	}

	public String getUrlKey(){
		return this.urlKey;
	}

	public PlayUrl setUrlKey(String urlKey) {
		this.urlKey = urlKey;
		return this;
	}
}