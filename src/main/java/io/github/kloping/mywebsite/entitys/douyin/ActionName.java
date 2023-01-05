package io.github.kloping.mywebsite.entitys.douyin;

public class ActionName {
	private String web;
	private String client;

	public String getWeb(){
		return this.web;
	}

	public ActionName setWeb(String web) {
		this.web = web;
		return this;
	}

	public String getClient(){
		return this.client;
	}

	public ActionName setClient(String client) {
		this.client = client;
		return this;
	}
}