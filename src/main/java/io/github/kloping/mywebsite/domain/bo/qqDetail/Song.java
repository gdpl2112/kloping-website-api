package io.github.kloping.mywebsite.domain.bo.qqDetail;

public class Song {
	private Number curnum;
	private Number curpage;
	private Number totalnum;
	private QQMusicDataList[] list;

	public Number getCurnum(){
		return this.curnum;
	}

	public Song setCurnum(Number curnum) {
		this.curnum = curnum;
		return this;
	}

	public Number getCurpage(){
		return this.curpage;
	}

	public Song setCurpage(Number curpage) {
		this.curpage = curpage;
		return this;
	}

	public Number getTotalnum(){
		return this.totalnum;
	}

	public Song setTotalnum(Number totalnum) {
		this.totalnum = totalnum;
		return this;
	}

	public QQMusicDataList[] getList(){
		return this.list;
	}

	public Song setList(QQMusicDataList[] list) {
		this.list = list;
		return this;
	}
}