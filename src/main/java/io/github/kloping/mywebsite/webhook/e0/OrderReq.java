package io.github.kloping.mywebsite.webhook.e0;

public class OrderReq {
	private Data data;
	private String em;
	private Number ec;

	public Data getData(){
		return this.data;
	}

	public OrderReq setData(Data data) {
		this.data = data;
		return this;
	}

	public String getEm(){
		return this.em;
	}

	public OrderReq setEm(String em) {
		this.em = em;
		return this;
	}

	public Number getEc(){
		return this.ec;
	}

	public OrderReq setEc(Number ec) {
		this.ec = ec;
		return this;
	}
}