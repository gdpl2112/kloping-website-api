package io.github.kloping.mywebsite.entitys.getVideo;

public class Data {
	private String year;
	private String name;
	private Source source;

	public String getYear(){
		return this.year;
	}

	public Data setYear(String year) {
		this.year = year;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Data setName(String name) {
		this.name = name;
		return this;
	}

	public Source getSource(){
		return this.source;
	}

	public Data setSource(Source source) {
		this.source = source;
		return this;
	}
}