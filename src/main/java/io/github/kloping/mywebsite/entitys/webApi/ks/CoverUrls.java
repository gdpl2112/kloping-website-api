package io.github.kloping.mywebsite.entitys.webApi.ks;

public class CoverUrls {
	private String cdn;
	private String url;

	public String getCdn(){
		return this.cdn;
	}

	public CoverUrls setCdn(String cdn) {
		this.cdn = cdn;
		return this;
	}

	public String getUrl(){
		return this.url;
	}

	public CoverUrls setUrl(String url) {
		this.url = url;
		return this;
	}
}