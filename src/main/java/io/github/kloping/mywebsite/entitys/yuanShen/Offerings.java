package io.github.kloping.mywebsite.entitys.yuanShen;

/**
 * @author HRS-Computer
 */
public class Offerings {
	private Number level;
	private String name;
	private String icon;

	public Number getLevel(){
		return this.level;
	}

	public Offerings setLevel(Number level) {
		this.level = level;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Offerings setName(String name) {
		this.name = name;
		return this;
	}

	public String getIcon(){
		return this.icon;
	}

	public Offerings setIcon(String icon) {
		this.icon = icon;
		return this;
	}
}