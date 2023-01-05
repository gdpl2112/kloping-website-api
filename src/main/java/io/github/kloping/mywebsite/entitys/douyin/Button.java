package io.github.kloping.mywebsite.entitys.douyin;

public class Button {
	private String text;
	private String url;

	public String getText(){
		return this.text;
	}

	public Button setText(String text) {
		this.text = text;
		return this;
	}

	public String getUrl(){
		return this.url;
	}

	public Button setUrl(String url) {
		this.url = url;
		return this;
	}
}