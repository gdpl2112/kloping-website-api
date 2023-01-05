package io.github.kloping.mywebsite.entitys.douyin;

public class AwemeControl {
	private Boolean canShowComment;
	private Boolean canForward;
	private Boolean canShare;
	private Boolean canComment;

	public Boolean getCanShowComment(){
		return this.canShowComment;
	}

	public AwemeControl setCanShowComment(Boolean canShowComment) {
		this.canShowComment = canShowComment;
		return this;
	}

	public Boolean getCanForward(){
		return this.canForward;
	}

	public AwemeControl setCanForward(Boolean canForward) {
		this.canForward = canForward;
		return this;
	}

	public Boolean getCanShare(){
		return this.canShare;
	}

	public AwemeControl setCanShare(Boolean canShare) {
		this.canShare = canShare;
		return this;
	}

	public Boolean getCanComment(){
		return this.canComment;
	}

	public AwemeControl setCanComment(Boolean canComment) {
		this.canComment = canComment;
		return this;
	}
}