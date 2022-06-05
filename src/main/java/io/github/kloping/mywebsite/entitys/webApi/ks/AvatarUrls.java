package io.github.kloping.mywebsite.entitys.webApi.ks;

public class AvatarUrls {
	private String cdn;
	private String url;

	public String getCdn(){
		return this.cdn;
	}

	public AvatarUrls setCdn(String cdn) {
		this.cdn = cdn;
		return this;
	}

	public String getUrl(){
		return this.url;
	}

	public AvatarUrls setUrl(String url) {
		this.url = url;
		return this;
	}
}