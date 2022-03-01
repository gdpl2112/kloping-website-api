package io.github.kloping.mywebsite.entitys.webApi.kugouDetail;

public class Black {
	private Number isblock;
	private Number type;

	public Number getIsblock(){
		return this.isblock;
	}

	public Black setIsblock(Number isblock) {
		this.isblock = isblock;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public Black setType(Number type) {
		this.type = type;
		return this;
	}
}