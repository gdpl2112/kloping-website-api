package io.github.kloping.mywebsite.entitys.douyin;

public class BitRateList {
	private Number isH265;
	private String playApi;
	private Number bitRate;
	private String gearName;
	private String videoFormat;
	private PlayAddr[] playAddr;
	private Number width;
	private Number qualityType;
	private Number height;

	public Number getIsH265(){
		return this.isH265;
	}

	public BitRateList setIsH265(Number isH265) {
		this.isH265 = isH265;
		return this;
	}

	public String getPlayApi(){
		return this.playApi;
	}

	public BitRateList setPlayApi(String playApi) {
		this.playApi = playApi;
		return this;
	}

	public Number getBitRate(){
		return this.bitRate;
	}

	public BitRateList setBitRate(Number bitRate) {
		this.bitRate = bitRate;
		return this;
	}

	public String getGearName(){
		return this.gearName;
	}

	public BitRateList setGearName(String gearName) {
		this.gearName = gearName;
		return this;
	}

	public String getVideoFormat(){
		return this.videoFormat;
	}

	public BitRateList setVideoFormat(String videoFormat) {
		this.videoFormat = videoFormat;
		return this;
	}

	public PlayAddr[] getPlayAddr(){
		return this.playAddr;
	}

	public BitRateList setPlayAddr(PlayAddr[] playAddr) {
		this.playAddr = playAddr;
		return this;
	}

	public Number getWidth(){
		return this.width;
	}

	public BitRateList setWidth(Number width) {
		this.width = width;
		return this;
	}

	public Number getQualityType(){
		return this.qualityType;
	}

	public BitRateList setQualityType(Number qualityType) {
		this.qualityType = qualityType;
		return this;
	}

	public Number getHeight(){
		return this.height;
	}

	public BitRateList setHeight(Number height) {
		this.height = height;
		return this;
	}
}