package io.github.kloping.mywebsite.entitys.douyin;

public class Backpack_status {
	private Number fifa_main_status;
	private Number second_screen_status;
	private Number introduce_status;
	private Number status;

	public Number getFifa_main_status(){
		return this.fifa_main_status;
	}

	public Backpack_status setFifa_main_status(Number fifa_main_status) {
		this.fifa_main_status = fifa_main_status;
		return this;
	}

	public Number getSecond_screen_status(){
		return this.second_screen_status;
	}

	public Backpack_status setSecond_screen_status(Number second_screen_status) {
		this.second_screen_status = second_screen_status;
		return this;
	}

	public Number getIntroduce_status(){
		return this.introduce_status;
	}

	public Backpack_status setIntroduce_status(Number introduce_status) {
		this.introduce_status = introduce_status;
		return this;
	}

	public Number getStatus(){
		return this.status;
	}

	public Backpack_status setStatus(Number status) {
		this.status = status;
		return this;
	}
}