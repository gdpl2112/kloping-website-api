package io.github.kloping.mywebsite.entitys.ks;

public class Variables {
	private String pcursor;
	private String page;
	private String keyword;

	public String getPcursor(){
		return this.pcursor;
	}

	public Variables setPcursor(String pcursor) {
		this.pcursor = pcursor;
		return this;
	}

	public String getPage(){
		return this.page;
	}

	public Variables setPage(String page) {
		this.page = page;
		return this;
	}

	public String getKeyword(){
		return this.keyword;
	}

	public Variables setKeyword(String keyword) {
		this.keyword = keyword;
		return this;
	}
}