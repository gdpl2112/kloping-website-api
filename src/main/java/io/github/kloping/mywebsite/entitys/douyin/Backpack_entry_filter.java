package io.github.kloping.mywebsite.entitys.douyin;

public class Backpack_entry_filter {
	private Number collection_guide;
	private Number client_download_guide;
	private Number login_btn;
	private Number tab_entry;

	public Number getCollection_guide(){
		return this.collection_guide;
	}

	public Backpack_entry_filter setCollection_guide(Number collection_guide) {
		this.collection_guide = collection_guide;
		return this;
	}

	public Number getClient_download_guide(){
		return this.client_download_guide;
	}

	public Backpack_entry_filter setClient_download_guide(Number client_download_guide) {
		this.client_download_guide = client_download_guide;
		return this;
	}

	public Number getLogin_btn(){
		return this.login_btn;
	}

	public Backpack_entry_filter setLogin_btn(Number login_btn) {
		this.login_btn = login_btn;
		return this;
	}

	public Number getTab_entry(){
		return this.tab_entry;
	}

	public Backpack_entry_filter setTab_entry(Number tab_entry) {
		this.tab_entry = tab_entry;
		return this;
	}
}