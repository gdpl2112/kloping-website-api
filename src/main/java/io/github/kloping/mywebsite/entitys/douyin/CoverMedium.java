package io.github.kloping.mywebsite.entitys.douyin;

public class CoverMedium {
	private Number width;
	private String[] urlList;
	private String uri;
	private Number height;

	public Number getWidth(){
		return this.width;
	}

	public CoverMedium setWidth(Number width) {
		this.width = width;
		return this;
	}

	public String[] getUrlList(){
		return this.urlList;
	}

	public CoverMedium setUrlList(String[] urlList) {
		this.urlList = urlList;
		return this;
	}

	public String getUri(){
		return this.uri;
	}

	public CoverMedium setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public Number getHeight(){
		return this.height;
	}

	public CoverMedium setHeight(Number height) {
		this.height = height;
		return this;
	}
}