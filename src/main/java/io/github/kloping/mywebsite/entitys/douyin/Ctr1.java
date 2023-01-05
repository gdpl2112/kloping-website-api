package io.github.kloping.mywebsite.entitys.douyin;

public class Ctr1 {
	private Number duration;
	private Number threshold;

	public Number getDuration(){
		return this.duration;
	}

	public Ctr1 setDuration(Number duration) {
		this.duration = duration;
		return this;
	}

	public Number getThreshold(){
		return this.threshold;
	}

	public Ctr1 setThreshold(Number threshold) {
		this.threshold = threshold;
		return this;
	}
}