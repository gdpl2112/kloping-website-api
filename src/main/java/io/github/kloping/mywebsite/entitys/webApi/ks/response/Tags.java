package io.github.kloping.mywebsite.entitys.webApi.ks.response;

public class Tags {
	private String __typename;
	private String name;
	private Number type;

	public String get__typename(){
		return this.__typename;
	}

	public Tags set__typename(String __typename) {
		this.__typename = __typename;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Tags setName(String name) {
		this.name = name;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public Tags setType(Number type) {
		this.type = type;
		return this;
	}
}