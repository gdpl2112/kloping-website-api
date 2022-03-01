package io.github.kloping.mywebsite.entitys.webApi.qqDetail;

public class Data {
	private Song song;
	private Zhida zhida;
	private Semantic semantic;
	private Number tab;
	private Number totaltime;
	private String keyword;
	private Number priority;

	public Song getSong(){
		return this.song;
	}

	public Data setSong(Song song) {
		this.song = song;
		return this;
	}

	public Zhida getZhida(){
		return this.zhida;
	}

	public Data setZhida(Zhida zhida) {
		this.zhida = zhida;
		return this;
	}

	public Semantic getSemantic(){
		return this.semantic;
	}

	public Data setSemantic(Semantic semantic) {
		this.semantic = semantic;
		return this;
	}

	public Number getTab(){
		return this.tab;
	}

	public Data setTab(Number tab) {
		this.tab = tab;
		return this;
	}

	public Number getTotaltime(){
		return this.totaltime;
	}

	public Data setTotaltime(Number totaltime) {
		this.totaltime = totaltime;
		return this;
	}

	public String getKeyword(){
		return this.keyword;
	}

	public Data setKeyword(String keyword) {
		this.keyword = keyword;
		return this;
	}

	public Number getPriority(){
		return this.priority;
	}

	public Data setPriority(Number priority) {
		this.priority = priority;
		return this;
	}
}