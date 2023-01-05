package io.github.kloping.mywebsite.entitys.douyin;

public class LoginGuideConfig {
	private Number hideLoginGuideEndTime;
	private Number hideLoginGuideStartTime;
	private Boolean hideLoginGuide;

	public Number getHideLoginGuideEndTime(){
		return this.hideLoginGuideEndTime;
	}

	public LoginGuideConfig setHideLoginGuideEndTime(Number hideLoginGuideEndTime) {
		this.hideLoginGuideEndTime = hideLoginGuideEndTime;
		return this;
	}

	public Number getHideLoginGuideStartTime(){
		return this.hideLoginGuideStartTime;
	}

	public LoginGuideConfig setHideLoginGuideStartTime(Number hideLoginGuideStartTime) {
		this.hideLoginGuideStartTime = hideLoginGuideStartTime;
		return this;
	}

	public Boolean getHideLoginGuide(){
		return this.hideLoginGuide;
	}

	public LoginGuideConfig setHideLoginGuide(Boolean hideLoginGuide) {
		this.hideLoginGuide = hideLoginGuide;
		return this;
	}
}