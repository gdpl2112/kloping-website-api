package io.github.kloping.mywebsite.entitys.webApi.pcwiqiyi;

public class Main_charactor {
	private String[] character;
	private String image_url;
	private String name;
	private Number id;

	public String[] getCharacter(){
		return this.character;
	}

	public Main_charactor setCharacter(String[] character) {
		this.character = character;
		return this;
	}

	public String getImage_url(){
		return this.image_url;
	}

	public Main_charactor setImage_url(String image_url) {
		this.image_url = image_url;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Main_charactor setName(String name) {
		this.name = name;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Main_charactor setId(Number id) {
		this.id = id;
		return this;
	}
}