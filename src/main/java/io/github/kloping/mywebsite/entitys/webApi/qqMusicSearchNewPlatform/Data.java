package io.github.kloping.mywebsite.entitys.webApi.qqMusicSearchNewPlatform;

public class Data {
	private Song song;
	private Zhida zhida;
	private Number totaltime;
	private Album album;
	private Mv mv;
	private Sematic sematic;
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

	public Number getTotaltime(){
		return this.totaltime;
	}

	public Data setTotaltime(Number totaltime) {
		this.totaltime = totaltime;
		return this;
	}

	public Album getAlbum(){
		return this.album;
	}

	public Data setAlbum(Album album) {
		this.album = album;
		return this;
	}

	public Mv getMv(){
		return this.mv;
	}

	public Data setMv(Mv mv) {
		this.mv = mv;
		return this;
	}

	public Sematic getSematic(){
		return this.sematic;
	}

	public Data setSematic(Sematic sematic) {
		this.sematic = sematic;
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