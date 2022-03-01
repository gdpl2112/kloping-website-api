package io.github.kloping.mywebsite.entitys.webApi.douyPic;

public class Share_info {
	private String share_title;
	private String share_desc;
	private String share_weibo_desc;

	public String getShare_title(){
		return this.share_title;
	}

	public Share_info setShare_title(String share_title) {
		this.share_title = share_title;
		return this;
	}

	public String getShare_desc(){
		return this.share_desc;
	}

	public Share_info setShare_desc(String share_desc) {
		this.share_desc = share_desc;
		return this;
	}

	public String getShare_weibo_desc(){
		return this.share_weibo_desc;
	}

	public Share_info setShare_weibo_desc(String share_weibo_desc) {
		this.share_weibo_desc = share_weibo_desc;
		return this;
	}
}