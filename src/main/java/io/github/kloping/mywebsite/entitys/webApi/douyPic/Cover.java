package io.github.kloping.mywebsite.entitys.webApi.douyPic;

public class Cover {
	private String[] url_list;
	private String uri;

	public String[] getUrl_list(){
		return this.url_list;
	}

	public Cover setUrl_list(String[] url_list) {
		this.url_list = url_list;
		return this;
	}

	public String getUri(){
		return this.uri;
	}

	public Cover setUri(String uri) {
		this.uri = uri;
		return this;
	}
}