package io.github.kloping.mywebsite.entitys.douyin;

public class Music {
	private String idStr;
	private ShareInfo shareInfo;
	private String secUid;
	private String author;
	private String album;
	private Number collectStat;
	private String mid;
	private String title;
	private String ownerNickname;
	private String musicName;
	private PlayUrl playUrl;
	private AvatarThumb avatarThumb;
	private Number bindedChallengeId;
	private Number duration;
	private Boolean isOriginal;
	private CoverThumb coverThumb;
	private Number userCount;
	private Extra extra;
	private CoverMedium coverMedium;
	private Boolean canNotPlay;
	private Number id;
	private Number status;

	public String getIdStr(){
		return this.idStr;
	}

	public Music setIdStr(String idStr) {
		this.idStr = idStr;
		return this;
	}

	public ShareInfo getShareInfo(){
		return this.shareInfo;
	}

	public Music setShareInfo(ShareInfo shareInfo) {
		this.shareInfo = shareInfo;
		return this;
	}

	public String getSecUid(){
		return this.secUid;
	}

	public Music setSecUid(String secUid) {
		this.secUid = secUid;
		return this;
	}

	public String getAuthor(){
		return this.author;
	}

	public Music setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getAlbum(){
		return this.album;
	}

	public Music setAlbum(String album) {
		this.album = album;
		return this;
	}

	public Number getCollectStat(){
		return this.collectStat;
	}

	public Music setCollectStat(Number collectStat) {
		this.collectStat = collectStat;
		return this;
	}

	public String getMid(){
		return this.mid;
	}

	public Music setMid(String mid) {
		this.mid = mid;
		return this;
	}

	public String getTitle(){
		return this.title;
	}

	public Music setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getOwnerNickname(){
		return this.ownerNickname;
	}

	public Music setOwnerNickname(String ownerNickname) {
		this.ownerNickname = ownerNickname;
		return this;
	}

	public String getMusicName(){
		return this.musicName;
	}

	public Music setMusicName(String musicName) {
		this.musicName = musicName;
		return this;
	}

	public PlayUrl getPlayUrl(){
		return this.playUrl;
	}

	public Music setPlayUrl(PlayUrl playUrl) {
		this.playUrl = playUrl;
		return this;
	}

	public AvatarThumb getAvatarThumb(){
		return this.avatarThumb;
	}

	public Music setAvatarThumb(AvatarThumb avatarThumb) {
		this.avatarThumb = avatarThumb;
		return this;
	}

	public Number getBindedChallengeId(){
		return this.bindedChallengeId;
	}

	public Music setBindedChallengeId(Number bindedChallengeId) {
		this.bindedChallengeId = bindedChallengeId;
		return this;
	}

	public Number getDuration(){
		return this.duration;
	}

	public Music setDuration(Number duration) {
		this.duration = duration;
		return this;
	}

	public Boolean getIsOriginal(){
		return this.isOriginal;
	}

	public Music setIsOriginal(Boolean isOriginal) {
		this.isOriginal = isOriginal;
		return this;
	}

	public CoverThumb getCoverThumb(){
		return this.coverThumb;
	}

	public Music setCoverThumb(CoverThumb coverThumb) {
		this.coverThumb = coverThumb;
		return this;
	}

	public Number getUserCount(){
		return this.userCount;
	}

	public Music setUserCount(Number userCount) {
		this.userCount = userCount;
		return this;
	}

	public Extra getExtra(){
		return this.extra;
	}

	public Music setExtra(Extra extra) {
		this.extra = extra;
		return this;
	}

	public CoverMedium getCoverMedium(){
		return this.coverMedium;
	}

	public Music setCoverMedium(CoverMedium coverMedium) {
		this.coverMedium = coverMedium;
		return this;
	}

	public Boolean getCanNotPlay(){
		return this.canNotPlay;
	}

	public Music setCanNotPlay(Boolean canNotPlay) {
		this.canNotPlay = canNotPlay;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Music setId(Number id) {
		this.id = id;
		return this;
	}

	public Number getStatus(){
		return this.status;
	}

	public Music setStatus(Number status) {
		this.status = status;
		return this;
	}
}