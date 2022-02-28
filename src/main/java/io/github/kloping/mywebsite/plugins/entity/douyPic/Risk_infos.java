package io.github.kloping.mywebsite.plugins.entity.douyPic;

public class Risk_infos {
	private Boolean warn;
	private Number type;
	private String content;

	public Boolean getWarn(){
		return this.warn;
	}

	public Risk_infos setWarn(Boolean warn) {
		this.warn = warn;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public Risk_infos setType(Number type) {
		this.type = type;
		return this;
	}

	public String getContent(){
		return this.content;
	}

	public Risk_infos setContent(String content) {
		this.content = content;
		return this;
	}
}