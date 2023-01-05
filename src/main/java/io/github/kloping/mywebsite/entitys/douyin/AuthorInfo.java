package io.github.kloping.mywebsite.entitys.douyin;

public class AuthorInfo {
	private String avatarUri;
	private Number followerStatus;
	private String enterpriseVerifyReason;
	private String secUid;
	private Number followStatus;
	private String roleTitle;
	private RoomData roomData;
	private String remarkName;
	private Number secret;
	private String customVerify;
	private AvatarThumb avatarThumb;
	private String uid;
	private Boolean isAdFake;
	private String nickname;
	private Number followerCount;
	private Number totalFavorited;

	public String getAvatarUri(){
		return this.avatarUri;
	}

	public AuthorInfo setAvatarUri(String avatarUri) {
		this.avatarUri = avatarUri;
		return this;
	}

	public Number getFollowerStatus(){
		return this.followerStatus;
	}

	public AuthorInfo setFollowerStatus(Number followerStatus) {
		this.followerStatus = followerStatus;
		return this;
	}

	public String getEnterpriseVerifyReason(){
		return this.enterpriseVerifyReason;
	}

	public AuthorInfo setEnterpriseVerifyReason(String enterpriseVerifyReason) {
		this.enterpriseVerifyReason = enterpriseVerifyReason;
		return this;
	}

	public String getSecUid(){
		return this.secUid;
	}

	public AuthorInfo setSecUid(String secUid) {
		this.secUid = secUid;
		return this;
	}

	public Number getFollowStatus(){
		return this.followStatus;
	}

	public AuthorInfo setFollowStatus(Number followStatus) {
		this.followStatus = followStatus;
		return this;
	}

	public String getRoleTitle(){
		return this.roleTitle;
	}

	public AuthorInfo setRoleTitle(String roleTitle) {
		this.roleTitle = roleTitle;
		return this;
	}

	public RoomData getRoomData(){
		return this.roomData;
	}

	public AuthorInfo setRoomData(RoomData roomData) {
		this.roomData = roomData;
		return this;
	}

	public String getRemarkName(){
		return this.remarkName;
	}

	public AuthorInfo setRemarkName(String remarkName) {
		this.remarkName = remarkName;
		return this;
	}

	public Number getSecret(){
		return this.secret;
	}

	public AuthorInfo setSecret(Number secret) {
		this.secret = secret;
		return this;
	}

	public String getCustomVerify(){
		return this.customVerify;
	}

	public AuthorInfo setCustomVerify(String customVerify) {
		this.customVerify = customVerify;
		return this;
	}

	public AvatarThumb getAvatarThumb(){
		return this.avatarThumb;
	}

	public AuthorInfo setAvatarThumb(AvatarThumb avatarThumb) {
		this.avatarThumb = avatarThumb;
		return this;
	}

	public String getUid(){
		return this.uid;
	}

	public AuthorInfo setUid(String uid) {
		this.uid = uid;
		return this;
	}

	public Boolean getIsAdFake(){
		return this.isAdFake;
	}

	public AuthorInfo setIsAdFake(Boolean isAdFake) {
		this.isAdFake = isAdFake;
		return this;
	}

	public String getNickname(){
		return this.nickname;
	}

	public AuthorInfo setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public Number getFollowerCount(){
		return this.followerCount;
	}

	public AuthorInfo setFollowerCount(Number followerCount) {
		this.followerCount = followerCount;
		return this;
	}

	public Number getTotalFavorited(){
		return this.totalFavorited;
	}

	public AuthorInfo setTotalFavorited(Number totalFavorited) {
		this.totalFavorited = totalFavorited;
		return this;
	}
}