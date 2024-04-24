package io.github.kloping.mywebsite.domain.bo.netEaseSongs;

public class Album {
	private Number publishTime;
	private String picId_str;
	private Artist artist;
	private String blurPicUrl;
	private String description;
	private String commentThreadId;
	private Number pic;
	private String type;
	private String tags;
	private String picUrl;
	private Number companyId;
	private Number size;
	private String briefDesc;
	private Number copyrightId;
	private Artists[] artists;
	private String name;
	private Number id;
	private Number picId;
	private Number status;

	public Number getPublishTime(){
		return this.publishTime;
	}

	public Album setPublishTime(Number publishTime) {
		this.publishTime = publishTime;
		return this;
	}

	public String getPicId_str(){
		return this.picId_str;
	}

	public Album setPicId_str(String picId_str) {
		this.picId_str = picId_str;
		return this;
	}

	public Artist getArtist(){
		return this.artist;
	}

	public Album setArtist(Artist artist) {
		this.artist = artist;
		return this;
	}

	public String getBlurPicUrl(){
		return this.blurPicUrl;
	}

	public Album setBlurPicUrl(String blurPicUrl) {
		this.blurPicUrl = blurPicUrl;
		return this;
	}

	public String getDescription(){
		return this.description;
	}

	public Album setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getCommentThreadId(){
		return this.commentThreadId;
	}

	public Album setCommentThreadId(String commentThreadId) {
		this.commentThreadId = commentThreadId;
		return this;
	}

	public Number getPic(){
		return this.pic;
	}

	public Album setPic(Number pic) {
		this.pic = pic;
		return this;
	}

	public String getType(){
		return this.type;
	}

	public Album setType(String type) {
		this.type = type;
		return this;
	}

	public String getTags(){
		return this.tags;
	}

	public Album setTags(String tags) {
		this.tags = tags;
		return this;
	}

	public String getPicUrl(){
		return this.picUrl;
	}

	public Album setPicUrl(String picUrl) {
		this.picUrl = picUrl;
		return this;
	}

	public Number getCompanyId(){
		return this.companyId;
	}

	public Album setCompanyId(Number companyId) {
		this.companyId = companyId;
		return this;
	}

	public Number getSize(){
		return this.size;
	}

	public Album setSize(Number size) {
		this.size = size;
		return this;
	}

	public String getBriefDesc(){
		return this.briefDesc;
	}

	public Album setBriefDesc(String briefDesc) {
		this.briefDesc = briefDesc;
		return this;
	}

	public Number getCopyrightId(){
		return this.copyrightId;
	}

	public Album setCopyrightId(Number copyrightId) {
		this.copyrightId = copyrightId;
		return this;
	}

	public Artists[] getArtists(){
		return this.artists;
	}

	public Album setArtists(Artists[] artists) {
		this.artists = artists;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Album setName(String name) {
		this.name = name;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Album setId(Number id) {
		this.id = id;
		return this;
	}

	public Number getPicId(){
		return this.picId;
	}

	public Album setPicId(Number picId) {
		this.picId = picId;
		return this;
	}

	public Number getStatus(){
		return this.status;
	}

	public Album setStatus(Number status) {
		this.status = status;
		return this;
	}
}