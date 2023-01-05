package io.github.kloping.mywebsite.entitys.douyin;

public class CoverThumb {
	private Number width;
	private String[] urlList;
	private String uri;
	private Number height;

	public Number getWidth(){
		return this.width;
	}

	public CoverThumb setWidth(Number width) {
		this.width = width;
		return this;
	}

	public String[] getUrlList(){
		return this.urlList;
	}

	public CoverThumb setUrlList(String[] urlList) {
		this.urlList = urlList;
		return this;
	}

	public String getUri(){
		return this.uri;
	}

	public CoverThumb setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public Number getHeight(){
		return this.height;
	}

	public CoverThumb setHeight(Number height) {
		this.height = height;
		return this;
	}
}