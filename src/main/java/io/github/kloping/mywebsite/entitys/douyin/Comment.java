package io.github.kloping.mywebsite.entitys.douyin;

public class Comment {
	private Number cursor;
	private Number total;
	private Number consumerTime;
	private LogPb logPb;
	private Comments[] comments;
	private Boolean hasMore;
	private Number statusCode;

	public Number getCursor(){
		return this.cursor;
	}

	public Comment setCursor(Number cursor) {
		this.cursor = cursor;
		return this;
	}

	public Number getTotal(){
		return this.total;
	}

	public Comment setTotal(Number total) {
		this.total = total;
		return this;
	}

	public Number getConsumerTime(){
		return this.consumerTime;
	}

	public Comment setConsumerTime(Number consumerTime) {
		this.consumerTime = consumerTime;
		return this;
	}

	public LogPb getLogPb(){
		return this.logPb;
	}

	public Comment setLogPb(LogPb logPb) {
		this.logPb = logPb;
		return this;
	}

	public Comments[] getComments(){
		return this.comments;
	}

	public Comment setComments(Comments[] comments) {
		this.comments = comments;
		return this;
	}

	public Boolean getHasMore(){
		return this.hasMore;
	}

	public Comment setHasMore(Boolean hasMore) {
		this.hasMore = hasMore;
		return this;
	}

	public Number getStatusCode(){
		return this.statusCode;
	}

	public Comment setStatusCode(Number statusCode) {
		this.statusCode = statusCode;
		return this;
	}
}