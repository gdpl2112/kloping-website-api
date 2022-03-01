package io.github.kloping.mywebsite.entitys.webApi.ks.response;

public class KsSearchResponse {
	private Data data;

	public Data getData(){
		return this.data;
	}

	public KsSearchResponse setData(Data data) {
		this.data = data;
		return this;
	}
}