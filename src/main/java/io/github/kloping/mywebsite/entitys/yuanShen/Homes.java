package io.github.kloping.mywebsite.entitys.yuanShen;

/**
 * @author HRS-Computer
 */
public class Homes {
	private Number level;
	private String comfort_level_name;
	private String name;
	private String icon;
	private String comfort_level_icon;
	private Number comfort_num;
	private Number visit_num;
	private Number item_num;

	public Number getLevel(){
		return this.level;
	}

	public Homes setLevel(Number level) {
		this.level = level;
		return this;
	}

	public String getComfort_level_name(){
		return this.comfort_level_name;
	}

	public Homes setComfort_level_name(String comfort_level_name) {
		this.comfort_level_name = comfort_level_name;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Homes setName(String name) {
		this.name = name;
		return this;
	}

	public String getIcon(){
		return this.icon;
	}

	public Homes setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getComfort_level_icon(){
		return this.comfort_level_icon;
	}

	public Homes setComfort_level_icon(String comfort_level_icon) {
		this.comfort_level_icon = comfort_level_icon;
		return this;
	}

	public Number getComfort_num(){
		return this.comfort_num;
	}

	public Homes setComfort_num(Number comfort_num) {
		this.comfort_num = comfort_num;
		return this;
	}

	public Number getVisit_num(){
		return this.visit_num;
	}

	public Homes setVisit_num(Number visit_num) {
		this.visit_num = visit_num;
		return this;
	}

	public Number getItem_num(){
		return this.item_num;
	}

	public Homes setItem_num(Number item_num) {
		this.item_num = item_num;
		return this;
	}
}