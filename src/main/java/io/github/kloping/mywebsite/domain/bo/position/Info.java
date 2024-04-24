package io.github.kloping.mywebsite.domain.bo.position;

public class Info {
	private Number time;
	private Number type;
	private Number error;

	public Number getTime(){
		return this.time;
	}

	public Info setTime(Number time) {
		this.time = time;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public Info setType(Number type) {
		this.type = type;
		return this;
	}

	public Number getError(){
		return this.error;
	}

	public Info setError(Number error) {
		this.error = error;
		return this;
	}
}