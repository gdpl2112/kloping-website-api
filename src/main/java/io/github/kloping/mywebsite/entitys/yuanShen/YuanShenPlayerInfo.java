package io.github.kloping.mywebsite.entitys.yuanShen;

/**
 * @author HRS-Computer
 */
public class YuanShenPlayerInfo {
	private Data data;
	private String message;
	private Number retcode;

	public Data getData(){
		return this.data;
	}

	public YuanShenPlayerInfo setData(Data data) {
		this.data = data;
		return this;
	}

	public String getMessage(){
		return this.message;
	}

	public YuanShenPlayerInfo setMessage(String message) {
		this.message = message;
		return this;
	}

	public Number getRetcode(){
		return this.retcode;
	}

	public YuanShenPlayerInfo setRetcode(Number retcode) {
		this.retcode = retcode;
		return this;
	}
}