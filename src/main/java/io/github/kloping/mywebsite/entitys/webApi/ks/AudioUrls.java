package io.github.kloping.mywebsite.entitys.webApi.ks;

public class AudioUrls {
	private String cdn;
	private String url;

	public String getCdn(){
		return this.cdn;
	}

	public AudioUrls setCdn(String cdn) {
		this.cdn = cdn;
		return this;
	}

	public String getUrl(){
		return this.url;
	}

	public AudioUrls setUrl(String url) {
		this.url = url;
		return this;
	}
}