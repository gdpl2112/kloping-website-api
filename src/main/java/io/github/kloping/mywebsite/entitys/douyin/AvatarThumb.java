package io.github.kloping.mywebsite.entitys.douyin;

public class AvatarThumb {
	private Number width;
	private String[] urlList;
	private String uri;
	private Number height;

	public Number getWidth(){
		return this.width;
	}

	public AvatarThumb setWidth(Number width) {
		this.width = width;
		return this;
	}

	public String[] getUrlList(){
		return this.urlList;
	}

	public AvatarThumb setUrlList(String[] urlList) {
		this.urlList = urlList;
		return this;
	}

	public String getUri(){
		return this.uri;
	}

	public AvatarThumb setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public Number getHeight(){
		return this.height;
	}

	public AvatarThumb setHeight(Number height) {
		this.height = height;
		return this;
	}
}