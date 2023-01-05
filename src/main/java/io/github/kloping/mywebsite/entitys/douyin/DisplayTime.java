package io.github.kloping.mywebsite.entitys.douyin;

public class DisplayTime {
	private Number start;
	private Number end;

	public Number getStart(){
		return this.start;
	}

	public DisplayTime setStart(Number start) {
		this.start = start;
		return this;
	}

	public Number getEnd(){
		return this.end;
	}

	public DisplayTime setEnd(Number end) {
		this.end = end;
		return this;
	}
}