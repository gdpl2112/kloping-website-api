package io.github.kloping.mywebsite.plugins.entity.QqLyric;

public class QQLyric {
	private Number code;
	private String lyric;
	private Number subcode;
	private Number retcode;
	private String trans;

	public Number getCode(){
		return this.code;
	}

	public QQLyric setCode(Number code) {
		this.code = code;
		return this;
	}

	public String getLyric(){
		return this.lyric;
	}

	public QQLyric setLyric(String lyric) {
		this.lyric = lyric;
		return this;
	}

	public Number getSubcode(){
		return this.subcode;
	}

	public QQLyric setSubcode(Number subcode) {
		this.subcode = subcode;
		return this;
	}

	public Number getRetcode(){
		return this.retcode;
	}

	public QQLyric setRetcode(Number retcode) {
		this.retcode = retcode;
		return this;
	}

	public String getTrans(){
		return this.trans;
	}

	public QQLyric setTrans(String trans) {
		this.trans = trans;
		return this;
	}
}