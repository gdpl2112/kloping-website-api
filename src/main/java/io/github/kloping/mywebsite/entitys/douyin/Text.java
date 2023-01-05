package io.github.kloping.mywebsite.entitys.douyin;

public class Text {
	private Number start_time;
	private Number end_time;
	private String text;

	public Number getStart_time(){
		return this.start_time;
	}

	public Text setStart_time(Number start_time) {
		this.start_time = start_time;
		return this;
	}

	public Number getEnd_time(){
		return this.end_time;
	}

	public Text setEnd_time(Number end_time) {
		this.end_time = end_time;
		return this;
	}

	public String getText(){
		return this.text;
	}

	public Text setText(String text) {
		this.text = text;
		return this;
	}
}