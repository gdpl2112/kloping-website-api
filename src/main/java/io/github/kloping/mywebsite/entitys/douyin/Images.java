package io.github.kloping.mywebsite.entitys.douyin;

public class Images {
	private String[] downloadUrlList;
	private Number width;
	private String[] urlList;
	private String uri;
	private Number height;

	public String[] getDownloadUrlList(){
		return this.downloadUrlList;
	}

	public Images setDownloadUrlList(String[] downloadUrlList) {
		this.downloadUrlList = downloadUrlList;
		return this;
	}

	public Number getWidth(){
		return this.width;
	}

	public Images setWidth(Number width) {
		this.width = width;
		return this;
	}

	public String[] getUrlList(){
		return this.urlList;
	}

	public Images setUrlList(String[] urlList) {
		this.urlList = urlList;
		return this;
	}

	public String getUri(){
		return this.uri;
	}

	public Images setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public Number getHeight(){
		return this.height;
	}

	public Images setHeight(Number height) {
		this.height = height;
		return this;
	}
}