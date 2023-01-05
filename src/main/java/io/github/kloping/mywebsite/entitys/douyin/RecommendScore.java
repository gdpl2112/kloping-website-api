package io.github.kloping.mywebsite.entitys.douyin;

public class RecommendScore {
	private Number showMix;
	private Number showHot;
	private Number showRelateSearch;

	public Number getShowMix(){
		return this.showMix;
	}

	public RecommendScore setShowMix(Number showMix) {
		this.showMix = showMix;
		return this;
	}

	public Number getShowHot(){
		return this.showHot;
	}

	public RecommendScore setShowHot(Number showHot) {
		this.showHot = showHot;
		return this;
	}

	public Number getShowRelateSearch(){
		return this.showRelateSearch;
	}

	public RecommendScore setShowRelateSearch(Number showRelateSearch) {
		this.showRelateSearch = showRelateSearch;
		return this;
	}
}