package io.github.kloping.mywebsite.domain.bo.fcgPlaySingleSong;

public class Pay {
	private Number pay_play;
	private Number pay_status;
	private Number price_track;
	private Number time_free;
	private Number price_album;
	private Number pay_month;
	private Number pay_down;

	public Number getPay_play(){
		return this.pay_play;
	}

	public Pay setPay_play(Number pay_play) {
		this.pay_play = pay_play;
		return this;
	}

	public Number getPay_status(){
		return this.pay_status;
	}

	public Pay setPay_status(Number pay_status) {
		this.pay_status = pay_status;
		return this;
	}

	public Number getPrice_track(){
		return this.price_track;
	}

	public Pay setPrice_track(Number price_track) {
		this.price_track = price_track;
		return this;
	}

	public Number getTime_free(){
		return this.time_free;
	}

	public Pay setTime_free(Number time_free) {
		this.time_free = time_free;
		return this;
	}

	public Number getPrice_album(){
		return this.price_album;
	}

	public Pay setPrice_album(Number price_album) {
		this.price_album = price_album;
		return this;
	}

	public Number getPay_month(){
		return this.pay_month;
	}

	public Pay setPay_month(Number pay_month) {
		this.pay_month = pay_month;
		return this;
	}

	public Number getPay_down(){
		return this.pay_down;
	}

	public Pay setPay_down(Number pay_down) {
		this.pay_down = pay_down;
		return this;
	}
}