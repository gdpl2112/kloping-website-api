package io.github.kloping.mywebsite.entitys.douyin;

public class Video {
	private String originCover;
	private BigThumbs[] bigThumbs;
	private String playApiH265;
	private Number duration;
	private String cover;
	private String playApi;
	private PlayAddr[] playAddr;
	private Number width;
	private String[] coverUrlList;
	private String coverUri;
	private Number height;
	private String ratio;

	public String getOriginCover(){
		return this.originCover;
	}

	public Video setOriginCover(String originCover) {
		this.originCover = originCover;
		return this;
	}

	public BigThumbs[] getBigThumbs(){
		return this.bigThumbs;
	}

	public Video setBigThumbs(BigThumbs[] bigThumbs) {
		this.bigThumbs = bigThumbs;
		return this;
	}

	public String getPlayApiH265(){
		return this.playApiH265;
	}

	public Video setPlayApiH265(String playApiH265) {
		this.playApiH265 = playApiH265;
		return this;
	}

	public Number getDuration(){
		return this.duration;
	}

	public Video setDuration(Number duration) {
		this.duration = duration;
		return this;
	}

	public String getCover(){
		return this.cover;
	}

	public Video setCover(String cover) {
		this.cover = cover;
		return this;
	}

	public String getPlayApi(){
		return this.playApi;
	}

	public Video setPlayApi(String playApi) {
		this.playApi = playApi;
		return this;
	}

	public PlayAddr[] getPlayAddr(){
		return this.playAddr;
	}

	public Video setPlayAddr(PlayAddr[] playAddr) {
		this.playAddr = playAddr;
		return this;
	}

	public Number getWidth(){
		return this.width;
	}

	public Video setWidth(Number width) {
		this.width = width;
		return this;
	}

	public String[] getCoverUrlList(){
		return this.coverUrlList;
	}

	public Video setCoverUrlList(String[] coverUrlList) {
		this.coverUrlList = coverUrlList;
		return this;
	}

	public String getCoverUri(){
		return this.coverUri;
	}

	public Video setCoverUri(String coverUri) {
		this.coverUri = coverUri;
		return this;
	}

	public Number getHeight(){
		return this.height;
	}

	public Video setHeight(Number height) {
		this.height = height;
		return this;
	}

	public String getRatio(){
		return this.ratio;
	}

	public Video setRatio(String ratio) {
		this.ratio = ratio;
		return this;
	}
}