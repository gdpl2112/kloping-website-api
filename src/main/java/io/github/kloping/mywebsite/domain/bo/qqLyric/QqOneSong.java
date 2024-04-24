package io.github.kloping.mywebsite.domain.bo.qqLyric;

public class QqOneSong {
	private Number start_ts;
	private String traceid;
	private Number code;
	private Req_0 req_0;
	private Number ts;

	public Number getStart_ts(){
		return this.start_ts;
	}

	public QqOneSong setStart_ts(Number start_ts) {
		this.start_ts = start_ts;
		return this;
	}

	public String getTraceid(){
		return this.traceid;
	}

	public QqOneSong setTraceid(String traceid) {
		this.traceid = traceid;
		return this;
	}

	public Number getCode(){
		return this.code;
	}

	public QqOneSong setCode(Number code) {
		this.code = code;
		return this;
	}

	public Req_0 getReq_0(){
		return this.req_0;
	}

	public QqOneSong setReq_0(Req_0 req_0) {
		this.req_0 = req_0;
		return this;
	}

	public Number getTs(){
		return this.ts;
	}

	public QqOneSong setTs(Number ts) {
		this.ts = ts;
		return this;
	}
}