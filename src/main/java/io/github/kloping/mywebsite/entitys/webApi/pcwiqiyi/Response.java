package io.github.kloping.mywebsite.entitys.webApi.pcwiqiyi;

public class Response {
	private String code;
	private Data data;

	public String getCode(){
		return this.code;
	}

	public Response setCode(String code) {
		this.code = code;
		return this;
	}

	public Data getData(){
		return this.data;
	}

	public Response setData(Data data) {
		this.data = data;
		return this;
	}
}