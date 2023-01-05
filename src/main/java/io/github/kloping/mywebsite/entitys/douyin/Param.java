package io.github.kloping.mywebsite.entitys.douyin;

public class Param {
	private Number cursor;
	private Number lastCursor;
	private String secUid;
	private Number hasMore;

	public Number getCursor(){
		return this.cursor;
	}

	public Param setCursor(Number cursor) {
		this.cursor = cursor;
		return this;
	}

	public Number getLastCursor(){
		return this.lastCursor;
	}

	public Param setLastCursor(Number lastCursor) {
		this.lastCursor = lastCursor;
		return this;
	}

	public String getSecUid(){
		return this.secUid;
	}

	public Param setSecUid(String secUid) {
		this.secUid = secUid;
		return this;
	}

	public Number getHasMore(){
		return this.hasMore;
	}

	public Param setHasMore(Number hasMore) {
		this.hasMore = hasMore;
		return this;
	}
}