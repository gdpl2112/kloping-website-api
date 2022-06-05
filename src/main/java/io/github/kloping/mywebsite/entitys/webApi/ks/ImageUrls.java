package io.github.kloping.mywebsite.entitys.webApi.ks;

public class ImageUrls {
	private String cdn;
	private String url;

	public String getCdn(){
		return this.cdn;
	}

	public ImageUrls setCdn(String cdn) {
		this.cdn = cdn;
		return this;
	}

	public String getUrl(){
		return this.url;
	}

	public ImageUrls setUrl(String url) {
		this.url = url;
		return this;
	}
}