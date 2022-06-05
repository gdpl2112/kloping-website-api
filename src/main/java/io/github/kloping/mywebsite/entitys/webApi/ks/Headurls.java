package io.github.kloping.mywebsite.entitys.webApi.ks;

public class Headurls {
	private String cdn;
	private String url;

	public String getCdn(){
		return this.cdn;
	}

	public Headurls setCdn(String cdn) {
		this.cdn = cdn;
		return this;
	}

	public String getUrl(){
		return this.url;
	}

	public Headurls setUrl(String url) {
		this.url = url;
		return this;
	}
}