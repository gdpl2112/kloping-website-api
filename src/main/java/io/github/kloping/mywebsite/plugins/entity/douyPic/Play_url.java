package io.github.kloping.mywebsite.plugins.entity.douyPic;

public class Play_url {
	private String[] url_list;
	private String uri;

	public String[] getUrl_list(){
		return this.url_list;
	}

	public Play_url setUrl_list(String[] url_list) {
		this.url_list = url_list;
		return this;
	}

	public String getUri(){
		return this.uri;
	}

	public Play_url setUri(String uri) {
		this.uri = uri;
		return this;
	}
}