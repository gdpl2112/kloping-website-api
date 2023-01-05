package io.github.kloping.mywebsite.entitys.douyin;

public class CommonSetting {
	private Boolean showFeedUserGuide;
	private Boolean showFriendTabPoint;
	private Boolean showFollowTabPoint;

	public Boolean getShowFeedUserGuide(){
		return this.showFeedUserGuide;
	}

	public CommonSetting setShowFeedUserGuide(Boolean showFeedUserGuide) {
		this.showFeedUserGuide = showFeedUserGuide;
		return this;
	}

	public Boolean getShowFriendTabPoint(){
		return this.showFriendTabPoint;
	}

	public CommonSetting setShowFriendTabPoint(Boolean showFriendTabPoint) {
		this.showFriendTabPoint = showFriendTabPoint;
		return this;
	}

	public Boolean getShowFollowTabPoint(){
		return this.showFollowTabPoint;
	}

	public CommonSetting setShowFollowTabPoint(Boolean showFollowTabPoint) {
		this.showFollowTabPoint = showFollowTabPoint;
		return this;
	}
}