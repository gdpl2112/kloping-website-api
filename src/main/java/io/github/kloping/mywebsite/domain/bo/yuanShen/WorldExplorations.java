package io.github.kloping.mywebsite.domain.bo.yuanShen;

/**
 * @author HRS-Computer
 */
public class WorldExplorations {
	private String strategy_url;
	private Number level;
	private String icon;
	private String map_url;
	private String type;
	private String background_image;
	private String inner_icon;
	private String cover;
	private Number exploration_percentage;
	private Number parent_id;
	private String name;
	private Number id;
	private Offerings[] offerings;

	public String getStrategy_url(){
		return this.strategy_url;
	}

	public WorldExplorations setStrategy_url(String strategy_url) {
		this.strategy_url = strategy_url;
		return this;
	}

	public Number getLevel(){
		return this.level;
	}

	public WorldExplorations setLevel(Number level) {
		this.level = level;
		return this;
	}

	public String getMap_url(){
		return this.map_url;
	}

	public WorldExplorations setMap_url(String map_url) {
		this.map_url = map_url;
		return this;
	}

	public String getIcon(){
		return this.icon;
	}

	public WorldExplorations setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public String getType(){
		return this.type;
	}

	public WorldExplorations setType(String type) {
		this.type = type;
		return this;
	}

	public String getBackground_image(){
		return this.background_image;
	}

	public WorldExplorations setBackground_image(String background_image) {
		this.background_image = background_image;
		return this;
	}

	public String getInner_icon(){
		return this.inner_icon;
	}

	public WorldExplorations setInner_icon(String inner_icon) {
		this.inner_icon = inner_icon;
		return this;
	}

	public String getCover(){
		return this.cover;
	}

	public WorldExplorations setCover(String cover) {
		this.cover = cover;
		return this;
	}

	public Number getExploration_percentage(){
		return this.exploration_percentage;
	}

	public WorldExplorations setExploration_percentage(Number exploration_percentage) {
		this.exploration_percentage = exploration_percentage;
		return this;
	}

	public Number getParent_id(){
		return this.parent_id;
	}

	public WorldExplorations setParent_id(Number parent_id) {
		this.parent_id = parent_id;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public WorldExplorations setName(String name) {
		this.name = name;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public WorldExplorations setId(Number id) {
		this.id = id;
		return this;
	}

	public Offerings[] getOfferings(){
		return this.offerings;
	}

	public WorldExplorations setOfferings(Offerings[] offerings) {
		this.offerings = offerings;
		return this;
	}
}