package io.github.kloping.mywebsite.entitys.webApi.tencentVideo;

public class Module_style_info {
	private String layout_type;
	private String css_name;

	public String getLayout_type(){
		return this.layout_type;
	}

	public Module_style_info setLayout_type(String layout_type) {
		this.layout_type = layout_type;
		return this;
	}

	public String getCss_name(){
		return this.css_name;
	}

	public Module_style_info setCss_name(String css_name) {
		this.css_name = css_name;
		return this;
	}
}