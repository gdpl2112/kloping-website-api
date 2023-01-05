package io.github.kloping.mywebsite.entitys.douyin;

public class DefaultNoteRelated {
	private String logPb;
	private Data data;
	private String statusMessage;
	private Number statusCode;

	public String getLogPb(){
		return this.logPb;
	}

	public DefaultNoteRelated setLogPb(String logPb) {
		this.logPb = logPb;
		return this;
	}

	public Data getData(){
		return this.data;
	}

	public DefaultNoteRelated setData(Data data) {
		this.data = data;
		return this;
	}

	public String getStatusMessage(){
		return this.statusMessage;
	}

	public DefaultNoteRelated setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
		return this;
	}

	public Number getStatusCode(){
		return this.statusCode;
	}

	public DefaultNoteRelated setStatusCode(Number statusCode) {
		this.statusCode = statusCode;
		return this;
	}
}