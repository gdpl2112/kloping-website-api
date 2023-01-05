package io.github.kloping.mywebsite.entitys.douyin;

public class Tag {
	private Boolean inReviewing;
	private Boolean isTop;
	private Boolean relationLabels;
	private Boolean isStory;
	private Number reviewStatus;

	public Boolean getInReviewing(){
		return this.inReviewing;
	}

	public Tag setInReviewing(Boolean inReviewing) {
		this.inReviewing = inReviewing;
		return this;
	}

	public Boolean getIsTop(){
		return this.isTop;
	}

	public Tag setIsTop(Boolean isTop) {
		this.isTop = isTop;
		return this;
	}

	public Boolean getRelationLabels(){
		return this.relationLabels;
	}

	public Tag setRelationLabels(Boolean relationLabels) {
		this.relationLabels = relationLabels;
		return this;
	}

	public Boolean getIsStory(){
		return this.isStory;
	}

	public Tag setIsStory(Boolean isStory) {
		this.isStory = isStory;
		return this;
	}

	public Number getReviewStatus(){
		return this.reviewStatus;
	}

	public Tag setReviewStatus(Number reviewStatus) {
		this.reviewStatus = reviewStatus;
		return this;
	}
}