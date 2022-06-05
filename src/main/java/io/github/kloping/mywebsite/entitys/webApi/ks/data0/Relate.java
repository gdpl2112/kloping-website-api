package io.github.kloping.mywebsite.entitys.webApi.ks.data0;

public class Relate {
	private String reason;
	private Feeds[] feeds;

	public String getReason(){
		return this.reason;
	}

	public Relate setReason(String reason) {
		this.reason = reason;
		return this;
	}

	public Feeds[] getFeeds(){
		return this.feeds;
	}

	public Relate setFeeds(Feeds[] feeds) {
		this.feeds = feeds;
		return this;
	}
}