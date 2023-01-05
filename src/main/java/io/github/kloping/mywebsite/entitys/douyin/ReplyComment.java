package io.github.kloping.mywebsite.entitys.douyin;

public class ReplyComment {
	private Number userDigged;
	private Boolean userBuried;
	private String labelText;
	private Number labelType;
	private Number diggCount;
	private StickyInfo stickyInfo;
	private String replyToUserId;
	private Number createTime;
	private String text;
	private User user;
	private Number replyTotal;
	private String cid;
	private Number status;

	public Number getUserDigged(){
		return this.userDigged;
	}

	public ReplyComment setUserDigged(Number userDigged) {
		this.userDigged = userDigged;
		return this;
	}

	public Boolean getUserBuried(){
		return this.userBuried;
	}

	public ReplyComment setUserBuried(Boolean userBuried) {
		this.userBuried = userBuried;
		return this;
	}

	public String getLabelText(){
		return this.labelText;
	}

	public ReplyComment setLabelText(String labelText) {
		this.labelText = labelText;
		return this;
	}

	public Number getLabelType(){
		return this.labelType;
	}

	public ReplyComment setLabelType(Number labelType) {
		this.labelType = labelType;
		return this;
	}

	public Number getDiggCount(){
		return this.diggCount;
	}

	public ReplyComment setDiggCount(Number diggCount) {
		this.diggCount = diggCount;
		return this;
	}

	public StickyInfo getStickyInfo(){
		return this.stickyInfo;
	}

	public ReplyComment setStickyInfo(StickyInfo stickyInfo) {
		this.stickyInfo = stickyInfo;
		return this;
	}

	public String getReplyToUserId(){
		return this.replyToUserId;
	}

	public ReplyComment setReplyToUserId(String replyToUserId) {
		this.replyToUserId = replyToUserId;
		return this;
	}

	public Number getCreateTime(){
		return this.createTime;
	}

	public ReplyComment setCreateTime(Number createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getText(){
		return this.text;
	}

	public ReplyComment setText(String text) {
		this.text = text;
		return this;
	}

	public User getUser(){
		return this.user;
	}

	public ReplyComment setUser(User user) {
		this.user = user;
		return this;
	}

	public Number getReplyTotal(){
		return this.replyTotal;
	}

	public ReplyComment setReplyTotal(Number replyTotal) {
		this.replyTotal = replyTotal;
		return this;
	}

	public String getCid(){
		return this.cid;
	}

	public ReplyComment setCid(String cid) {
		this.cid = cid;
		return this;
	}

	public Number getStatus(){
		return this.status;
	}

	public ReplyComment setStatus(Number status) {
		this.status = status;
		return this;
	}
}