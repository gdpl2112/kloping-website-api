package io.github.kloping.mywebsite.entitys.douyin;

public class Comments {
	private Number userDigged;
	private Boolean userBuried;
	private String labelText;
	private Number labelType;
	private Number diggCount;
	private StickyInfo stickyInfo;
	private String replyToUserId;
	private Number createTime;
	private Number isTop;
	private ReplyComment[] replyComment;
	private String text;
	private User user;
	private Number replyTotal;
	private String cid;
	private Number status;

	public Number getUserDigged(){
		return this.userDigged;
	}

	public Comments setUserDigged(Number userDigged) {
		this.userDigged = userDigged;
		return this;
	}

	public Boolean getUserBuried(){
		return this.userBuried;
	}

	public Comments setUserBuried(Boolean userBuried) {
		this.userBuried = userBuried;
		return this;
	}

	public String getLabelText(){
		return this.labelText;
	}

	public Comments setLabelText(String labelText) {
		this.labelText = labelText;
		return this;
	}

	public Number getLabelType(){
		return this.labelType;
	}

	public Comments setLabelType(Number labelType) {
		this.labelType = labelType;
		return this;
	}

	public Number getDiggCount(){
		return this.diggCount;
	}

	public Comments setDiggCount(Number diggCount) {
		this.diggCount = diggCount;
		return this;
	}

	public StickyInfo getStickyInfo(){
		return this.stickyInfo;
	}

	public Comments setStickyInfo(StickyInfo stickyInfo) {
		this.stickyInfo = stickyInfo;
		return this;
	}

	public String getReplyToUserId(){
		return this.replyToUserId;
	}

	public Comments setReplyToUserId(String replyToUserId) {
		this.replyToUserId = replyToUserId;
		return this;
	}

	public Number getCreateTime(){
		return this.createTime;
	}

	public Comments setCreateTime(Number createTime) {
		this.createTime = createTime;
		return this;
	}

	public Number getIsTop(){
		return this.isTop;
	}

	public Comments setIsTop(Number isTop) {
		this.isTop = isTop;
		return this;
	}

	public ReplyComment[] getReplyComment(){
		return this.replyComment;
	}

	public Comments setReplyComment(ReplyComment[] replyComment) {
		this.replyComment = replyComment;
		return this;
	}

	public String getText(){
		return this.text;
	}

	public Comments setText(String text) {
		this.text = text;
		return this;
	}

	public User getUser(){
		return this.user;
	}

	public Comments setUser(User user) {
		this.user = user;
		return this;
	}

	public Number getReplyTotal(){
		return this.replyTotal;
	}

	public Comments setReplyTotal(Number replyTotal) {
		this.replyTotal = replyTotal;
		return this;
	}

	public String getCid(){
		return this.cid;
	}

	public Comments setCid(String cid) {
		this.cid = cid;
		return this;
	}

	public Number getStatus(){
		return this.status;
	}

	public Comments setStatus(Number status) {
		this.status = status;
		return this;
	}
}