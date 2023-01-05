package io.github.kloping.mywebsite.entitys.douyin;

public class Backpack_header_text {
	private Number start_time;
	private Number end_time;
	private String text;

	public Number getStart_time(){
		return this.start_time;
	}

	public Backpack_header_text setStart_time(Number start_time) {
		this.start_time = start_time;
		return this;
	}

	public Number getEnd_time(){
		return this.end_time;
	}

	public Backpack_header_text setEnd_time(Number end_time) {
		this.end_time = end_time;
		return this;
	}

	public String getText(){
		return this.text;
	}

	public Backpack_header_text setText(String text) {
		this.text = text;
		return this;
	}
}