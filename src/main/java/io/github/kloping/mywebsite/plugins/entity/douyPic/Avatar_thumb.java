package io.github.kloping.mywebsite.plugins.entity.douyPic;

public class Avatar_thumb {
	private String[] url_list;
	private String uri;

	public String[] getUrl_list(){
		return this.url_list;
	}

	public Avatar_thumb setUrl_list(String[] url_list) {
		this.url_list = url_list;
		return this;
	}

	public String getUri(){
		return this.uri;
	}

	public Avatar_thumb setUri(String uri) {
		this.uri = uri;
		return this;
	}
}