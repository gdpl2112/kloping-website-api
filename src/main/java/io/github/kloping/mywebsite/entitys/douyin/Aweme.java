package io.github.kloping.mywebsite.entitys.douyin;

public class Aweme {
	private Boolean isUnknownAweme;
	private String logPb;
	private Detail detail;
	private Number statusCode;

	public Boolean getIsUnknownAweme(){
		return this.isUnknownAweme;
	}

	public Aweme setIsUnknownAweme(Boolean isUnknownAweme) {
		this.isUnknownAweme = isUnknownAweme;
		return this;
	}

	public String getLogPb(){
		return this.logPb;
	}

	public Aweme setLogPb(String logPb) {
		this.logPb = logPb;
		return this;
	}

	public Detail getDetail(){
		return this.detail;
	}

	public Aweme setDetail(Detail detail) {
		this.detail = detail;
		return this;
	}

	public Number getStatusCode(){
		return this.statusCode;
	}

	public Aweme setStatusCode(Number statusCode) {
		this.statusCode = statusCode;
		return this;
	}
}