package io.github.kloping.mywebsite.entitys.douyin;

public class TextExtra {
	private String secUid;
	private Number searchHideWords;
	private Number start;
	private String hashtagName;
	private Boolean isCommerce;
	private Number type;
	private String awemeId;
	private String userId;
	private Number searchRank;
	private String searchText;
	private String hashtagId;
	private Number end;
	private String searchQueryId;

	public String getSecUid(){
		return this.secUid;
	}

	public TextExtra setSecUid(String secUid) {
		this.secUid = secUid;
		return this;
	}

	public Number getSearchHideWords(){
		return this.searchHideWords;
	}

	public TextExtra setSearchHideWords(Number searchHideWords) {
		this.searchHideWords = searchHideWords;
		return this;
	}

	public Number getStart(){
		return this.start;
	}

	public TextExtra setStart(Number start) {
		this.start = start;
		return this;
	}

	public Boolean getIsCommerce(){
		return this.isCommerce;
	}

	public TextExtra setIsCommerce(Boolean isCommerce) {
		this.isCommerce = isCommerce;
		return this;
	}

	public String getHashtagName(){
		return this.hashtagName;
	}

	public TextExtra setHashtagName(String hashtagName) {
		this.hashtagName = hashtagName;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public TextExtra setType(Number type) {
		this.type = type;
		return this;
	}

	public String getAwemeId(){
		return this.awemeId;
	}

	public TextExtra setAwemeId(String awemeId) {
		this.awemeId = awemeId;
		return this;
	}

	public String getUserId(){
		return this.userId;
	}

	public TextExtra setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Number getSearchRank(){
		return this.searchRank;
	}

	public TextExtra setSearchRank(Number searchRank) {
		this.searchRank = searchRank;
		return this;
	}

	public String getSearchText(){
		return this.searchText;
	}

	public TextExtra setSearchText(String searchText) {
		this.searchText = searchText;
		return this;
	}

	public String getHashtagId(){
		return this.hashtagId;
	}

	public TextExtra setHashtagId(String hashtagId) {
		this.hashtagId = hashtagId;
		return this;
	}

	public Number getEnd(){
		return this.end;
	}

	public TextExtra setEnd(Number end) {
		this.end = end;
		return this;
	}

	public String getSearchQueryId(){
		return this.searchQueryId;
	}

	public TextExtra setSearchQueryId(String searchQueryId) {
		this.searchQueryId = searchQueryId;
		return this;
	}
}