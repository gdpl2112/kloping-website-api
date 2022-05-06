package io.github.kloping.mywebsite.entitys.webApi.tencentVideo;

public class Item_datas {
	private String item_id;
	private Sub_items sub_items;
	private String item_type;
	private String item_source_type;
	private Item_params item_params;
	private String complex_json;

	public String getItem_id(){
		return this.item_id;
	}

	public Item_datas setItem_id(String item_id) {
		this.item_id = item_id;
		return this;
	}

	public Sub_items getSub_items(){
		return this.sub_items;
	}

	public Item_datas setSub_items(Sub_items sub_items) {
		this.sub_items = sub_items;
		return this;
	}

	public String getItem_type(){
		return this.item_type;
	}

	public Item_datas setItem_type(String item_type) {
		this.item_type = item_type;
		return this;
	}

	public String getItem_source_type(){
		return this.item_source_type;
	}

	public Item_datas setItem_source_type(String item_source_type) {
		this.item_source_type = item_source_type;
		return this;
	}

	public Item_params getItem_params(){
		return this.item_params;
	}

	public Item_datas setItem_params(Item_params item_params) {
		this.item_params = item_params;
		return this;
	}

	public String getComplex_json(){
		return this.complex_json;
	}

	public Item_datas setComplex_json(String complex_json) {
		this.complex_json = complex_json;
		return this;
	}
}