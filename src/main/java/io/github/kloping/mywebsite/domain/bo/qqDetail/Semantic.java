package io.github.kloping.mywebsite.domain.bo.qqDetail;

public class Semantic {
	private Number curnum;
	private Number curpage;
	private Number totalnum;

	public Number getCurnum(){
		return this.curnum;
	}

	public Semantic setCurnum(Number curnum) {
		this.curnum = curnum;
		return this;
	}

	public Number getCurpage(){
		return this.curpage;
	}

	public Semantic setCurpage(Number curpage) {
		this.curpage = curpage;
		return this;
	}

	public Number getTotalnum(){
		return this.totalnum;
	}

	public Semantic setTotalnum(Number totalnum) {
		this.totalnum = totalnum;
		return this;
	}
}