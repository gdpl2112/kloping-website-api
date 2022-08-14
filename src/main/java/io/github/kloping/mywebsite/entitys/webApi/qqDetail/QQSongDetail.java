package io.github.kloping.mywebsite.entitys.webApi.qqDetail;

/**
 * @author HRS-Computer
 */
public class QQSongDetail {
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

	public QQSongDetail setCode(Number code) {
		this.code = code;
		return this;
	}

	public Data getData(){
		return this.data;
	}

	public QQSongDetail setData(Data data) {
		this.data = data;
		return this;
	}

	public Number getSubcode(){
		return this.subcode;
	}

	public QQSongDetail setSubcode(Number subcode) {
		this.subcode = subcode;
		return this;
	}

	public Number getTime(){
		return this.time;
	}

	public QQSongDetail setTime(Number time) {
		this.time = time;
		return this;
	}

	public String getMessage(){
		return this.message;
	}

	public QQSongDetail setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getTips(){
		return this.tips;
	}

	public QQSongDetail setTips(String tips) {
		this.tips = tips;
		return this;
	}

	public String getNotice(){
		return this.notice;
	}

	public QQSongDetail setNotice(String notice) {
		this.notice = notice;
		return this;
	}
}