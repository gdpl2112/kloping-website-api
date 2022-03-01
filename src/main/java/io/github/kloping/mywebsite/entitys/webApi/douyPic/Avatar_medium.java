package io.github.kloping.mywebsite.entitys.webApi.douyPic;

public class Avatar_medium {
	private String[] url_list;
	private String uri;

	public String[] getUrl_list(){
		return this.url_list;
	}

	public Avatar_medium setUrl_list(String[] url_list) {
		this.url_list = url_list;
		return this;
	}

	public String getUri(){
		return this.uri;
	}

	public Avatar_medium setUri(String uri) {
		this.uri = uri;
		return this;
	}
}