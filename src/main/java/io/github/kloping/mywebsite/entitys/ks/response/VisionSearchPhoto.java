package io.github.kloping.mywebsite.entitys.ks.response;

public class VisionSearchPhoto {
	private Number result;
	private String webPageArea;
	private String __typename;
	private Feeds[] feeds;
	private String pcursor;
	private String llsid;
	private String searchSessionId;

	public Number getResult(){
		return this.result;
	}

	public VisionSearchPhoto setResult(Number result) {
		this.result = result;
		return this;
	}

	public String getWebPageArea(){
		return this.webPageArea;
	}

	public VisionSearchPhoto setWebPageArea(String webPageArea) {
		this.webPageArea = webPageArea;
		return this;
	}

	public String get__typename(){
		return this.__typename;
	}

	public VisionSearchPhoto set__typename(String __typename) {
		this.__typename = __typename;
		return this;
	}

	public Feeds[] getFeeds(){
		return this.feeds;
	}

	public VisionSearchPhoto setFeeds(Feeds[] feeds) {
		this.feeds = feeds;
		return this;
	}

	public String getPcursor(){
		return this.pcursor;
	}

	public VisionSearchPhoto setPcursor(String pcursor) {
		this.pcursor = pcursor;
		return this;
	}

	public String getLlsid(){
		return this.llsid;
	}

	public VisionSearchPhoto setLlsid(String llsid) {
		this.llsid = llsid;
		return this;
	}

	public String getSearchSessionId(){
		return this.searchSessionId;
	}

	public VisionSearchPhoto setSearchSessionId(String searchSessionId) {
		this.searchSessionId = searchSessionId;
		return this;
	}
}