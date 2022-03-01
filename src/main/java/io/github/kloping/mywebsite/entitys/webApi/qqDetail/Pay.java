package io.github.kloping.mywebsite.entitys.webApi.qqDetail;

public class Pay {
	private Number payplay;
	private Number payalbum;
	private Number paydownload;
	private Number paytrackmouth;
	private Number paytrackprice;
	private Number payalbumprice;
	private Number payinfo;

	public Number getPayplay(){
		return this.payplay;
	}

	public Pay setPayplay(Number payplay) {
		this.payplay = payplay;
		return this;
	}

	public Number getPayalbum(){
		return this.payalbum;
	}

	public Pay setPayalbum(Number payalbum) {
		this.payalbum = payalbum;
		return this;
	}

	public Number getPaydownload(){
		return this.paydownload;
	}

	public Pay setPaydownload(Number paydownload) {
		this.paydownload = paydownload;
		return this;
	}

	public Number getPaytrackmouth(){
		return this.paytrackmouth;
	}

	public Pay setPaytrackmouth(Number paytrackmouth) {
		this.paytrackmouth = paytrackmouth;
		return this;
	}

	public Number getPaytrackprice(){
		return this.paytrackprice;
	}

	public Pay setPaytrackprice(Number paytrackprice) {
		this.paytrackprice = paytrackprice;
		return this;
	}

	public Number getPayalbumprice(){
		return this.payalbumprice;
	}

	public Pay setPayalbumprice(Number payalbumprice) {
		this.payalbumprice = payalbumprice;
		return this;
	}

	public Number getPayinfo(){
		return this.payinfo;
	}

	public Pay setPayinfo(Number payinfo) {
		this.payinfo = payinfo;
		return this;
	}
}