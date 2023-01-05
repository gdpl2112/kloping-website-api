package io.github.kloping.mywebsite.entitys.douyin;

public class WebRawData {
	private CTR CTR;
	private String recomPhrase;
	private VideoImageInfo videoImageInfo;
	private OftenWatchInfo oftenWatchInfo;

	public CTR getCTR(){
		return this.CTR;
	}

	public WebRawData setCTR(CTR CTR) {
		this.CTR = CTR;
		return this;
	}

	public String getRecomPhrase(){
		return this.recomPhrase;
	}

	public WebRawData setRecomPhrase(String recomPhrase) {
		this.recomPhrase = recomPhrase;
		return this;
	}

	public VideoImageInfo getVideoImageInfo(){
		return this.videoImageInfo;
	}

	public WebRawData setVideoImageInfo(VideoImageInfo videoImageInfo) {
		this.videoImageInfo = videoImageInfo;
		return this;
	}

	public OftenWatchInfo getOftenWatchInfo(){
		return this.oftenWatchInfo;
	}

	public WebRawData setOftenWatchInfo(OftenWatchInfo oftenWatchInfo) {
		this.oftenWatchInfo = oftenWatchInfo;
		return this;
	}
}