package io.github.kloping.mywebsite.domain.bo.qqMusicSearchNewPlatform;

public class Sematic {
	private Number curnum;
	private Number curpage;
	private Number totalnum;

	public Number getCurnum(){
		return this.curnum;
	}

	public Sematic setCurnum(Number curnum) {
		this.curnum = curnum;
		return this;
	}

	public Number getCurpage(){
		return this.curpage;
	}

	public Sematic setCurpage(Number curpage) {
		this.curpage = curpage;
		return this;
	}

	public Number getTotalnum(){
		return this.totalnum;
	}

	public Sematic setTotalnum(Number totalnum) {
		this.totalnum = totalnum;
		return this;
	}
}