package io.github.kloping.mywebsite.entitys.douyin;

public class Backpack_timeline {
	private Number start_time;
	private Number end_time;
	private Number status;

	public Number getStart_time(){
		return this.start_time;
	}

	public Backpack_timeline setStart_time(Number start_time) {
		this.start_time = start_time;
		return this;
	}

	public Number getEnd_time(){
		return this.end_time;
	}

	public Backpack_timeline setEnd_time(Number end_time) {
		this.end_time = end_time;
		return this;
	}

	public Number getStatus(){
		return this.status;
	}

	public Backpack_timeline setStatus(Number status) {
		this.status = status;
		return this;
	}
}