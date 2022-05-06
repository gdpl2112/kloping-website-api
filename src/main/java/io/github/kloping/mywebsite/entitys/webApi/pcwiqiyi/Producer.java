package io.github.kloping.mywebsite.entitys.webApi.pcwiqiyi;

public class Producer {
	private String name;
	private Number id;

	public String getName(){
		return this.name;
	}

	public Producer setName(String name) {
		this.name = name;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Producer setId(Number id) {
		this.id = id;
		return this;
	}
}