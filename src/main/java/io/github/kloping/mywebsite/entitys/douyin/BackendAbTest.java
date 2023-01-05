package io.github.kloping.mywebsite.entitys.douyin;

public class BackendAbTest {
	private Number feedbarPolicy;
	private Danmaku danmaku;

	public Number getFeedbarPolicy(){
		return this.feedbarPolicy;
	}

	public BackendAbTest setFeedbarPolicy(Number feedbarPolicy) {
		this.feedbarPolicy = feedbarPolicy;
		return this;
	}

	public Danmaku getDanmaku(){
		return this.danmaku;
	}

	public BackendAbTest setDanmaku(Danmaku danmaku) {
		this.danmaku = danmaku;
		return this;
	}
}