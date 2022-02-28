package io.github.kloping.mywebsite.plugins.entity.douyPic;

public class Extra {
	private Number now;
	private String logid;

	public Number getNow(){
		return this.now;
	}

	public Extra setNow(Number now) {
		this.now = now;
		return this;
	}

	public String getLogid(){
		return this.logid;
	}

	public Extra setLogid(String logid) {
		this.logid = logid;
		return this;
	}
}