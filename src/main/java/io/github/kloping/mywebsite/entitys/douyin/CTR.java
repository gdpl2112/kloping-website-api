package io.github.kloping.mywebsite.entitys.douyin;

public class CTR {
	private RecommendScore recommendScore;

	public RecommendScore getRecommendScore(){
		return this.recommendScore;
	}

	public CTR setRecommendScore(RecommendScore recommendScore) {
		this.recommendScore = recommendScore;
		return this;
	}
}