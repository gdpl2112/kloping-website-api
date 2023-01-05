package io.github.kloping.mywebsite.entitys.douyin;

public class ImConfig {
	private Number pullInterval;

	public Number getPullInterval(){
		return this.pullInterval;
	}

	public ImConfig setPullInterval(Number pullInterval) {
		this.pullInterval = pullInterval;
		return this;
	}
}