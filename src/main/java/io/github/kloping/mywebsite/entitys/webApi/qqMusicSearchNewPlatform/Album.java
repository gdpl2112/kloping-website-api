package io.github.kloping.mywebsite.entitys.webApi.qqMusicSearchNewPlatform;

public class Album {
	private Number curnum;
	private Number curpage;
	private Number totalnum;

	public Number getCurnum(){
		return this.curnum;
	}

	public Album setCurnum(Number curnum) {
		this.curnum = curnum;
		return this;
	}

	public Number getCurpage(){
		return this.curpage;
	}

	public Album setCurpage(Number curpage) {
		this.curpage = curpage;
		return this;
	}

	public Number getTotalnum(){
		return this.totalnum;
	}

	public Album setTotalnum(Number totalnum) {
		this.totalnum = totalnum;
		return this;
	}
}