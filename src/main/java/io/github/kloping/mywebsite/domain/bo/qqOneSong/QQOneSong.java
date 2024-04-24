package io.github.kloping.mywebsite.domain.bo.qqOneSong;

public class QQOneSong {
	private Number start_ts;
	private String traceid;
	private Number code;
	private Req_0 req_0;
	private Number ts;

	public Number getStart_ts(){
		return this.start_ts;
	}

	public QQOneSong setStart_ts(Number start_ts) {
		this.start_ts = start_ts;
		return this;
	}

	public String getTraceid(){
		return this.traceid;
	}

	public QQOneSong setTraceid(String traceid) {
		this.traceid = traceid;
		return this;
	}

	public Number getCode(){
		return this.code;
	}

	public QQOneSong setCode(Number code) {
		this.code = code;
		return this;
	}

	public Req_0 getReq_0(){
		return this.req_0;
	}

	public QQOneSong setReq_0(Req_0 req_0) {
		this.req_0 = req_0;
		return this;
	}

	public Number getTs(){
		return this.ts;
	}

	public QQOneSong setTs(Number ts) {
		this.ts = ts;
		return this;
	}
}