package io.github.kloping.mywebsite.entitys.douyin;

public class Vs_spring_module {
	private Number min;
	private Number moduleId;
	private String title;

	public Number getMin(){
		return this.min;
	}

	public Vs_spring_module setMin(Number min) {
		this.min = min;
		return this;
	}

	public Number getModuleId(){
		return this.moduleId;
	}

	public Vs_spring_module setModuleId(Number moduleId) {
		this.moduleId = moduleId;
		return this;
	}

	public String getTitle(){
		return this.title;
	}

	public Vs_spring_module setTitle(String title) {
		this.title = title;
		return this;
	}
}