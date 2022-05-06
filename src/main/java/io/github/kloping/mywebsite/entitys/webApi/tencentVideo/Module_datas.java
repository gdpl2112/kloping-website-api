package io.github.kloping.mywebsite.entitys.webApi.tencentVideo;

public class Module_datas {
	private String module_id;
	private Item_data_lists item_data_lists;
	private Module_style_info module_style_info;
	private Page_context page_context;
	private Boolean has_page_context;
	private Module_params module_params;

	public String getModule_id(){
		return this.module_id;
	}

	public Module_datas setModule_id(String module_id) {
		this.module_id = module_id;
		return this;
	}

	public Item_data_lists getItem_data_lists(){
		return this.item_data_lists;
	}

	public Module_datas setItem_data_lists(Item_data_lists item_data_lists) {
		this.item_data_lists = item_data_lists;
		return this;
	}

	public Module_style_info getModule_style_info(){
		return this.module_style_info;
	}

	public Module_datas setModule_style_info(Module_style_info module_style_info) {
		this.module_style_info = module_style_info;
		return this;
	}

	public Page_context getPage_context(){
		return this.page_context;
	}

	public Module_datas setPage_context(Page_context page_context) {
		this.page_context = page_context;
		return this;
	}

	public Boolean getHas_page_context(){
		return this.has_page_context;
	}

	public Module_datas setHas_page_context(Boolean has_page_context) {
		this.has_page_context = has_page_context;
		return this;
	}

	public Module_params getModule_params(){
		return this.module_params;
	}

	public Module_datas setModule_params(Module_params module_params) {
		this.module_params = module_params;
		return this;
	}
}