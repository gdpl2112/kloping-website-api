package io.github.kloping.mywebsite.entitys.douyin;

public class DefaultSeoRelatedAweme {
	private String logPb;
	private Boolean hasMore;
	private Number statusCode;

	public String getLogPb(){
		return this.logPb;
	}

	public DefaultSeoRelatedAweme setLogPb(String logPb) {
		this.logPb = logPb;
		return this;
	}

	public Boolean getHasMore(){
		return this.hasMore;
	}

	public DefaultSeoRelatedAweme setHasMore(Boolean hasMore) {
		this.hasMore = hasMore;
		return this;
	}

	public Number getStatusCode(){
		return this.statusCode;
	}

	public DefaultSeoRelatedAweme setStatusCode(Number statusCode) {
		this.statusCode = statusCode;
		return this;
	}
}