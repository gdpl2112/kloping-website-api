package io.github.kloping.mywebsite.entitys.douyin;

public class Search {
	private Number enable_world_cup_recall;
	private Number enable_zero_risk_list;

	public Number getEnable_world_cup_recall(){
		return this.enable_world_cup_recall;
	}

	public Search setEnable_world_cup_recall(Number enable_world_cup_recall) {
		this.enable_world_cup_recall = enable_world_cup_recall;
		return this;
	}

	public Number getEnable_zero_risk_list(){
		return this.enable_zero_risk_list;
	}

	public Search setEnable_zero_risk_list(Number enable_zero_risk_list) {
		this.enable_zero_risk_list = enable_zero_risk_list;
		return this;
	}
}