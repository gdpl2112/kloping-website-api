package io.github.kloping.mywebsite.entitys.douyin;

public class Image {
	private IsExpand isExpand;
	private IsFold isFold;

	public IsExpand getIsExpand(){
		return this.isExpand;
	}

	public Image setIsExpand(IsExpand isExpand) {
		this.isExpand = isExpand;
		return this;
	}

	public IsFold getIsFold(){
		return this.isFold;
	}

	public Image setIsFold(IsFold isFold) {
		this.isFold = isFold;
		return this;
	}
}