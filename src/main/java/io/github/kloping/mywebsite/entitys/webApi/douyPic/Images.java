package io.github.kloping.mywebsite.entitys.webApi.douyPic;

/**
 * @author HRS-Computer
 */
public class Images {
	private String[] url_list;
	private Number width;
	private String uri;
	private String[] download_url_list;
	private Number height;

	public String[] getUrl_list(){
		return this.url_list;
	}

	public Images setUrl_list(String[] url_list) {
		this.url_list = url_list;
		return this;
	}

	public Number getWidth(){
		return this.width;
	}

	public Images setWidth(Number width) {
		this.width = width;
		return this;
	}

	public String getUri(){
		return this.uri;
	}

	public Images setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public String[] getDownload_url_list(){
		return this.download_url_list;
	}

	public Images setDownload_url_list(String[] download_url_list) {
		this.download_url_list = download_url_list;
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