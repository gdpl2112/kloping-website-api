package io.github.kloping.mywebsite.entitys.webApi.fcgPlaySingleSong;

public class FcgPlaySingleSong {
	private Number code;
	private Data[] data;
	private Object url1;
	private Number msgid;
	private Number joox_login;
	private Object url;
	private Number joox;

	public Number getCode(){
		return this.code;
	}

	public FcgPlaySingleSong setCode(Number code) {
		this.code = code;
		return this;
	}

	public Data[] getData(){
		return this.data;
	}

	public FcgPlaySingleSong setData(Data[] data) {
		this.data = data;
		return this;
	}

	public Object getObject1(){
		return this.url1;
	}

	public FcgPlaySingleSong setObject1(Object url1) {
		this.url1 = url1;
		return this;
	}

	public Number getMsgid(){
		return this.msgid;
	}

	public FcgPlaySingleSong setMsgid(Number msgid) {
		this.msgid = msgid;
		return this;
	}

	public Number getJoox_login(){
		return this.joox_login;
	}

	public FcgPlaySingleSong setJoox_login(Number joox_login) {
		this.joox_login = joox_login;
		return this;
	}

	public Object getObject(){
		return this.url;
	}

	public FcgPlaySingleSong setObject(Object url) {
		this.url = url;
		return this;
	}

	public Number getJoox(){
		return this.joox;
	}

	public FcgPlaySingleSong setJoox(Number joox) {
		this.joox = joox;
		return this;
	}
}