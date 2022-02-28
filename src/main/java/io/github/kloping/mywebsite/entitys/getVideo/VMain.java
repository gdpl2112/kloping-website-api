package io.github.kloping.mywebsite.entitys.getVideo;

public class VMain {
	private Data[] data;
	private Y[] y;
	private String ep;
	private String type;
	private Number sp;

	public Data[] getData(){
		return this.data;
	}

	public VMain setData(Data[] data) {
		this.data = data;
		return this;
	}

	public Y[] getY(){
		return this.y;
	}

	public VMain setY(Y[] y) {
		this.y = y;
		return this;
	}

	public String getEp(){
		return this.ep;
	}

	public VMain setEp(String ep) {
		this.ep = ep;
		return this;
	}

	public String getType(){
		return this.type;
	}

	public VMain setType(String type) {
		this.type = type;
		return this;
	}

	public Number getSp(){
		return this.sp;
	}

	public VMain setSp(Number sp) {
		this.sp = sp;
		return this;
	}
}