package io.github.kloping.mywebsite.entitys.ks.response;

public class PhotoUrls {
	private String __typename;
	private String cdn;
	private String url;

	public String get__typename(){
		return this.__typename;
	}

	public PhotoUrls set__typename(String __typename) {
		this.__typename = __typename;
		return this;
	}

	public String getCdn(){
		return this.cdn;
	}

	public PhotoUrls setCdn(String cdn) {
		this.cdn = cdn;
		return this;
	}

	public String getUrl(){
		return this.url;
	}

	public PhotoUrls setUrl(String url) {
		this.url = url;
		return this;
	}
}