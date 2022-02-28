package io.github.kloping.mywebsite.plugins.entity.douyPic;

public class DouyPic {
	private Number status_code;
	private Item_list[] item_list;
	private Extra extra;

	public Number getStatus_code(){
		return this.status_code;
	}

	public DouyPic setStatus_code(Number status_code) {
		this.status_code = status_code;
		return this;
	}

	public Item_list[] getItem_list(){
		return this.item_list;
	}

	public DouyPic setItem_list(Item_list[] item_list) {
		this.item_list = item_list;
		return this;
	}

	public Extra getExtra(){
		return this.extra;
	}

	public DouyPic setExtra(Extra extra) {
		this.extra = extra;
		return this;
	}
}