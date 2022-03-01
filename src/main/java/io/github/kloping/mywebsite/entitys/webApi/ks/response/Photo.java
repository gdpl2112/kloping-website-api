package io.github.kloping.mywebsite.entitys.webApi.ks.response;

public class Photo {
	private Number videoRatio;
	private PhotoUrls[] photoUrls;
	private String __typename;
	private Number stereoType;
	private String caption;
	private String likeCount;
	private Number realLikeCount;
	private String animatedCoverUrl;
	private Boolean liked;
	private Number duration;
	private String coverUrl;
	private String photoUrl;
	private String expTag;
	private String id;
	private Number timestamp;

	public Number getVideoRatio(){
		return this.videoRatio;
	}

	public Photo setVideoRatio(Number videoRatio) {
		this.videoRatio = videoRatio;
		return this;
	}

	public PhotoUrls[] getPhotoUrls(){
		return this.photoUrls;
	}

	public Photo setPhotoUrls(PhotoUrls[] photoUrls) {
		this.photoUrls = photoUrls;
		return this;
	}

	public String get__typename(){
		return this.__typename;
	}

	public Photo set__typename(String __typename) {
		this.__typename = __typename;
		return this;
	}

	public Number getStereoType(){
		return this.stereoType;
	}

	public Photo setStereoType(Number stereoType) {
		this.stereoType = stereoType;
		return this;
	}

	public String getCaption(){
		return this.caption;
	}

	public Photo setCaption(String caption) {
		this.caption = caption;
		return this;
	}

	public String getLikeCount(){
		return this.likeCount;
	}

	public Photo setLikeCount(String likeCount) {
		this.likeCount = likeCount;
		return this;
	}

	public Number getRealLikeCount(){
		return this.realLikeCount;
	}

	public Photo setRealLikeCount(Number realLikeCount) {
		this.realLikeCount = realLikeCount;
		return this;
	}

	public String getAnimatedCoverUrl(){
		return this.animatedCoverUrl;
	}

	public Photo setAnimatedCoverUrl(String animatedCoverUrl) {
		this.animatedCoverUrl = animatedCoverUrl;
		return this;
	}

	public Boolean getLiked(){
		return this.liked;
	}

	public Photo setLiked(Boolean liked) {
		this.liked = liked;
		return this;
	}

	public Number getDuration(){
		return this.duration;
	}

	public Photo setDuration(Number duration) {
		this.duration = duration;
		return this;
	}

	public String getCoverUrl(){
		return this.coverUrl;
	}

	public Photo setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
		return this;
	}

	public String getPhotoUrl(){
		return this.photoUrl;
	}

	public Photo setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
		return this;
	}

	public String getExpTag(){
		return this.expTag;
	}

	public Photo setExpTag(String expTag) {
		this.expTag = expTag;
		return this;
	}

	public String getId(){
		return this.id;
	}

	public Photo setId(String id) {
		this.id = id;
		return this;
	}

	public Number getTimestamp(){
		return this.timestamp;
	}

	public Photo setTimestamp(Number timestamp) {
		this.timestamp = timestamp;
		return this;
	}
}