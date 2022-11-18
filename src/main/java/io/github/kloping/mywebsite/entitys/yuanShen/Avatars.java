package io.github.kloping.mywebsite.entitys.yuanShen;

/**
 * @author HRS-Computer
 */
public class Avatars {
	private String image;
	private Boolean is_chosen;
	private Number level;
	private String name;
	private Number fetter;
	private Number id;
	private Number actived_constellation_num;
	private String card_image;
	private String element;
	private Number rarity;

	public String getImage(){
		return this.image;
	}

	public Avatars setImage(String image) {
		this.image = image;
		return this;
	}

	public Boolean getIs_chosen(){
		return this.is_chosen;
	}

	public Avatars setIs_chosen(Boolean is_chosen) {
		this.is_chosen = is_chosen;
		return this;
	}

	public Number getLevel(){
		return this.level;
	}

	public Avatars setLevel(Number level) {
		this.level = level;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Avatars setName(String name) {
		this.name = name;
		return this;
	}

	public Number getFetter(){
		return this.fetter;
	}

	public Avatars setFetter(Number fetter) {
		this.fetter = fetter;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Avatars setId(Number id) {
		this.id = id;
		return this;
	}

	public Number getActived_constellation_num(){
		return this.actived_constellation_num;
	}

	public Avatars setActived_constellation_num(Number actived_constellation_num) {
		this.actived_constellation_num = actived_constellation_num;
		return this;
	}

	public String getCard_image(){
		return this.card_image;
	}

	public Avatars setCard_image(String card_image) {
		this.card_image = card_image;
		return this;
	}

	public String getElement(){
		return this.element;
	}

	public Avatars setElement(String element) {
		this.element = element;
		return this;
	}

	public Number getRarity(){
		return this.rarity;
	}

	public Avatars setRarity(Number rarity) {
		this.rarity = rarity;
		return this;
	}
}