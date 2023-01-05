package io.github.kloping.mywebsite.entitys.douyin;

public class Status {
	private Boolean isReviewing;
	private Number privateStatus;
	private Boolean isDelete;
	private Number reviewStatus;
	private Boolean isProhibited;
	private Boolean allowShare;

	public Boolean getIsReviewing(){
		return this.isReviewing;
	}

	public Status setIsReviewing(Boolean isReviewing) {
		this.isReviewing = isReviewing;
		return this;
	}

	public Number getPrivateStatus(){
		return this.privateStatus;
	}

	public Status setPrivateStatus(Number privateStatus) {
		this.privateStatus = privateStatus;
		return this;
	}

	public Boolean getIsDelete(){
		return this.isDelete;
	}

	public Status setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
		return this;
	}

	public Number getReviewStatus(){
		return this.reviewStatus;
	}

	public Status setReviewStatus(Number reviewStatus) {
		this.reviewStatus = reviewStatus;
		return this;
	}

	public Boolean getIsProhibited(){
		return this.isProhibited;
	}

	public Status setIsProhibited(Boolean isProhibited) {
		this.isProhibited = isProhibited;
		return this;
	}

	public Boolean getAllowShare(){
		return this.allowShare;
	}

	public Status setAllowShare(Boolean allowShare) {
		this.allowShare = allowShare;
		return this;
	}
}