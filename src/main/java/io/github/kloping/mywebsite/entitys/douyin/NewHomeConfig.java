package io.github.kloping.mywebsite.entitys.douyin;

public class NewHomeConfig {
	private Number showCollectTagMinCount;
	private Number stayDurationForGuide;
	private Number redirectCookieDuration;
	private String bannerVersion;
	private Number canRedirectCount;
	private Number showCommentTagMinCount;

	public Number getShowCollectTagMinCount(){
		return this.showCollectTagMinCount;
	}

	public NewHomeConfig setShowCollectTagMinCount(Number showCollectTagMinCount) {
		this.showCollectTagMinCount = showCollectTagMinCount;
		return this;
	}

	public Number getStayDurationForGuide(){
		return this.stayDurationForGuide;
	}

	public NewHomeConfig setStayDurationForGuide(Number stayDurationForGuide) {
		this.stayDurationForGuide = stayDurationForGuide;
		return this;
	}

	public Number getRedirectCookieDuration(){
		return this.redirectCookieDuration;
	}

	public NewHomeConfig setRedirectCookieDuration(Number redirectCookieDuration) {
		this.redirectCookieDuration = redirectCookieDuration;
		return this;
	}

	public String getBannerVersion(){
		return this.bannerVersion;
	}

	public NewHomeConfig setBannerVersion(String bannerVersion) {
		this.bannerVersion = bannerVersion;
		return this;
	}

	public Number getCanRedirectCount(){
		return this.canRedirectCount;
	}

	public NewHomeConfig setCanRedirectCount(Number canRedirectCount) {
		this.canRedirectCount = canRedirectCount;
		return this;
	}

	public Number getShowCommentTagMinCount(){
		return this.showCommentTagMinCount;
	}

	public NewHomeConfig setShowCommentTagMinCount(Number showCommentTagMinCount) {
		this.showCommentTagMinCount = showCommentTagMinCount;
		return this;
	}
}