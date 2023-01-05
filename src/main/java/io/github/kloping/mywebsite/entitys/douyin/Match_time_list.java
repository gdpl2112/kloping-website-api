package io.github.kloping.mywebsite.entitys.douyin;

public class Match_time_list {
	private Number start_time;
	private Number end_time;
	private String text;

	public Number getStart_time(){
		return this.start_time;
	}

	public Match_time_list setStart_time(Number start_time) {
		this.start_time = start_time;
		return this;
	}

	public Number getEnd_time(){
		return this.end_time;
	}

	public Match_time_list setEnd_time(Number end_time) {
		this.end_time = end_time;
		return this;
	}

	public String getText(){
		return this.text;
	}

	public Match_time_list setText(String text) {
		this.text = text;
		return this;
	}
}