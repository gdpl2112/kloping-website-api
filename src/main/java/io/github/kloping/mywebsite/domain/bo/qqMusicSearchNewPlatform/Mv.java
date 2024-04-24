package io.github.kloping.mywebsite.domain.bo.qqMusicSearchNewPlatform;

public class Mv {
	private Number curnum;
	private Number curpage;
	private Number totalnum;

	public Number getCurnum(){
		return this.curnum;
	}

	public Mv setCurnum(Number curnum) {
		this.curnum = curnum;
		return this;
	}

	public Number getCurpage(){
		return this.curpage;
	}

	public Mv setCurpage(Number curpage) {
		this.curpage = curpage;
		return this;
	}

	public Number getTotalnum(){
		return this.totalnum;
	}

	public Mv setTotalnum(Number totalnum) {
		this.totalnum = totalnum;
		return this;
	}
}