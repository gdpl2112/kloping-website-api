package io.github.kloping.mywebsite.entitys.douyin;

public class Live_small_window {
	private Number durationTime;
	private Number showTime2;
	private Number showTime1;
	private Number restrictTime;
	private Number ratio;

	public Number getDurationTime(){
		return this.durationTime;
	}

	public Live_small_window setDurationTime(Number durationTime) {
		this.durationTime = durationTime;
		return this;
	}

	public Number getShowTime2(){
		return this.showTime2;
	}

	public Live_small_window setShowTime2(Number showTime2) {
		this.showTime2 = showTime2;
		return this;
	}

	public Number getShowTime1(){
		return this.showTime1;
	}

	public Live_small_window setShowTime1(Number showTime1) {
		this.showTime1 = showTime1;
		return this;
	}

	public Number getRestrictTime(){
		return this.restrictTime;
	}

	public Live_small_window setRestrictTime(Number restrictTime) {
		this.restrictTime = restrictTime;
		return this;
	}

	public Number getRatio(){
		return this.ratio;
	}

	public Live_small_window setRatio(Number ratio) {
		this.ratio = ratio;
		return this;
	}
}