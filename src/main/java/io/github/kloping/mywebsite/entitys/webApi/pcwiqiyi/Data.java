package io.github.kloping.mywebsite.entitys.webApi.pcwiqiyi;

public class Data {
	private Number part;
	private Boolean hasMore;
	private String albumId;
	private Epsodelist[] epsodelist;
	private Number videoCount;
	private Number total;
	private Number latestOrder;
	private Number size;
	private Vipprevuelist[] vipprevuelist;
	private Number page;

	public Number getVideoCount(){
		return this.videoCount;
	}

	public Data setVideoCount(Number videoCount) {
		this.videoCount = videoCount;
		return this;
	}

	public Number getTotal(){
		return this.total;
	}

	public Data setTotal(Number total) {
		this.total = total;
		return this;
	}

	public Number getLatestOrder(){
		return this.latestOrder;
	}

	public Data setLatestOrder(Number latestOrder) {
		this.latestOrder = latestOrder;
		return this;
	}

	public Number getSize(){
		return this.size;
	}

	public Data setSize(Number size) {
		this.size = size;
		return this;
	}

	public Number getPart(){
		return this.part;
	}

	public Data setPart(Number part) {
		this.part = part;
		return this;
	}

	public Vipprevuelist[] getVipprevuelist(){
		return this.vipprevuelist;
	}

	public Data setVipprevuelist(Vipprevuelist[] vipprevuelist) {
		this.vipprevuelist = vipprevuelist;
		return this;
	}

	public Boolean getHasMore(){
		return this.hasMore;
	}

	public Data setHasMore(Boolean hasMore) {
		this.hasMore = hasMore;
		return this;
	}

	public String getAlbumId(){
		return this.albumId;
	}

	public Data setAlbumId(String albumId) {
		this.albumId = albumId;
		return this;
	}

	public Number getPage(){
		return this.page;
	}

	public Data setPage(Number page) {
		this.page = page;
		return this;
	}

	public Epsodelist[] getEpsodelist(){
		return this.epsodelist;
	}

	public Data setEpsodelist(Epsodelist[] epsodelist) {
		this.epsodelist = epsodelist;
		return this;
	}
}