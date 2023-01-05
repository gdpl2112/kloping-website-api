package io.github.kloping.mywebsite.entitys.douyin;

public class Res {
	private Data[] data;
	private Number hasMore;
	private String awemeId;
	private Number statusCode;

	public Data[] getData(){
		return this.data;
	}

	public Res setData(Data[] data) {
		this.data = data;
		return this;
	}

	public Number getHasMore(){
		return this.hasMore;
	}

	public Res setHasMore(Number hasMore) {
		this.hasMore = hasMore;
		return this;
	}

	public String getAwemeId(){
		return this.awemeId;
	}

	public Res setAwemeId(String awemeId) {
		this.awemeId = awemeId;
		return this;
	}

	public Number getStatusCode(){
		return this.statusCode;
	}

	public Res setStatusCode(Number statusCode) {
		this.statusCode = statusCode;
		return this;
	}
}