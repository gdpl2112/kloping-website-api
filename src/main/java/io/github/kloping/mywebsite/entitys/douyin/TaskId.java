package io.github.kloping.mywebsite.entitys.douyin;

public class TaskId {
	private String web;
	private String client;

	public String getWeb(){
		return this.web;
	}

	public TaskId setWeb(String web) {
		this.web = web;
		return this;
	}

	public String getClient(){
		return this.client;
	}

	public TaskId setClient(String client) {
		this.client = client;
		return this;
	}
}