package io.github.kloping.mywebsite.entitys.webApi.tencentVideo;

public class Data {
	private Pre_page_context pre_page_context;
	private Request_context request_context;
	private Boolean has_next_page;
	private Page_info page_info;
	private Boolean has_pre_page;
	private Module_list_datas[] module_list_datas;
	private Next_page_context next_page_context;

	public Pre_page_context getPre_page_context(){
		return this.pre_page_context;
	}

	public Data setPre_page_context(Pre_page_context pre_page_context) {
		this.pre_page_context = pre_page_context;
		return this;
	}

	public Request_context getRequest_context(){
		return this.request_context;
	}

	public Data setRequest_context(Request_context request_context) {
		this.request_context = request_context;
		return this;
	}

	public Boolean getHas_next_page(){
		return this.has_next_page;
	}

	public Data setHas_next_page(Boolean has_next_page) {
		this.has_next_page = has_next_page;
		return this;
	}

	public Page_info getPage_info(){
		return this.page_info;
	}

	public Data setPage_info(Page_info page_info) {
		this.page_info = page_info;
		return this;
	}

	public Boolean getHas_pre_page(){
		return this.has_pre_page;
	}

	public Data setHas_pre_page(Boolean has_pre_page) {
		this.has_pre_page = has_pre_page;
		return this;
	}

	public Module_list_datas[] getModule_list_datas(){
		return this.module_list_datas;
	}

	public Data setModule_list_datas(Module_list_datas[] module_list_datas) {
		this.module_list_datas = module_list_datas;
		return this;
	}

	public Next_page_context getNext_page_context(){
		return this.next_page_context;
	}

	public Data setNext_page_context(Next_page_context next_page_context) {
		this.next_page_context = next_page_context;
		return this;
	}
}