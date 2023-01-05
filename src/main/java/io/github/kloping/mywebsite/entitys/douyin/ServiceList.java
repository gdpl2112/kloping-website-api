package io.github.kloping.mywebsite.entitys.douyin;

public class ServiceList {
	private String name;
	private String icon;
	private Number id;
	private String jumpUrl;

	public String getName(){
		return this.name;
	}

	public ServiceList setName(String name) {
		this.name = name;
		return this;
	}

	public String getIcon(){
		return this.icon;
	}

	public ServiceList setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public ServiceList setId(Number id) {
		this.id = id;
		return this;
	}

	public String getJumpUrl(){
		return this.jumpUrl;
	}

	public ServiceList setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
		return this;
	}
}