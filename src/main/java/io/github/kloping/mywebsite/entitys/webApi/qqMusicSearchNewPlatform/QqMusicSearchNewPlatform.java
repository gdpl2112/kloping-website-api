package io.github.kloping.mywebsite.entitys.webApi.qqMusicSearchNewPlatform;

public class QqMusicSearchNewPlatform {
	private Number code;
	private Data data;
	private Number subcode;
	private Number time;
	private String message;
	private String tips;
	private String notice;

	public Number getCode(){
		return this.code;
	}

	public QqMusicSearchNewPlatform setCode(Number code) {
		this.code = code;
		return this;
	}

	public Data getData(){
		return this.data;
	}

	public QqMusicSearchNewPlatform setData(Data data) {
		this.data = data;
		return this;
	}

	public Number getSubcode(){
		return this.subcode;
	}

	public QqMusicSearchNewPlatform setSubcode(Number subcode) {
		this.subcode = subcode;
		return this;
	}

	public Number getTime(){
		return this.time;
	}

	public QqMusicSearchNewPlatform setTime(Number time) {
		this.time = time;
		return this;
	}

	public String getMessage(){
		return this.message;
	}

	public QqMusicSearchNewPlatform setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getTips(){
		return this.tips;
	}

	public QqMusicSearchNewPlatform setTips(String tips) {
		this.tips = tips;
		return this;
	}

	public String getNotice(){
		return this.notice;
	}

	public QqMusicSearchNewPlatform setNotice(String notice) {
		this.notice = notice;
		return this;
	}
}