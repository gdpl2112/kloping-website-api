package io.github.kloping.mywebsite.webhook.e0;

public class Data {
	private String type;
	private Order order;

	public String getType(){
		return this.type;
	}

	public Data setType(String type) {
		this.type = type;
		return this;
	}

	public Order getOrder(){
		return this.order;
	}

	public Data setOrder(Order order) {
		this.order = order;
		return this;
	}
}