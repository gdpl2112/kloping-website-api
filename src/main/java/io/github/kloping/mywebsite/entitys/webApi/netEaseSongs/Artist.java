package io.github.kloping.mywebsite.entitys.webApi.netEaseSongs;

public class Artist {
	private String picUrl;
	private String img1v1Url;
	private String briefDesc;
	private Number musicSize;
	private String name;
	private Number img1v1Id;
	private Number id;
	private Number picId;
	private Number albumSize;
	private String trans;

	public String getPicUrl(){
		return this.picUrl;
	}

	public Artist setPicUrl(String picUrl) {
		this.picUrl = picUrl;
		return this;
	}

	public String getImg1v1Url(){
		return this.img1v1Url;
	}

	public Artist setImg1v1Url(String img1v1Url) {
		this.img1v1Url = img1v1Url;
		return this;
	}

	public String getBriefDesc(){
		return this.briefDesc;
	}

	public Artist setBriefDesc(String briefDesc) {
		this.briefDesc = briefDesc;
		return this;
	}

	public Number getMusicSize(){
		return this.musicSize;
	}

	public Artist setMusicSize(Number musicSize) {
		this.musicSize = musicSize;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Artist setName(String name) {
		this.name = name;
		return this;
	}

	public Number getImg1v1Id(){
		return this.img1v1Id;
	}

	public Artist setImg1v1Id(Number img1v1Id) {
		this.img1v1Id = img1v1Id;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Artist setId(Number id) {
		this.id = id;
		return this;
	}

	public Number getPicId(){
		return this.picId;
	}

	public Artist setPicId(Number picId) {
		this.picId = picId;
		return this;
	}

	public Number getAlbumSize(){
		return this.albumSize;
	}

	public Artist setAlbumSize(Number albumSize) {
		this.albumSize = albumSize;
		return this;
	}

	public String getTrans(){
		return this.trans;
	}

	public Artist setTrans(String trans) {
		this.trans = trans;
		return this;
	}
}