package io.github.kloping.mywebsite.entitys.douyin;

public class MixInfo {
	private String cover;
	private Author author;
	private Number hasUpdatedEpisode;
	private Number isCollected;
	private Number status;

	public String getCover(){
		return this.cover;
	}

	public MixInfo setCover(String cover) {
		this.cover = cover;
		return this;
	}

	public Author getAuthor(){
		return this.author;
	}

	public MixInfo setAuthor(Author author) {
		this.author = author;
		return this;
	}

	public Number getHasUpdatedEpisode(){
		return this.hasUpdatedEpisode;
	}

	public MixInfo setHasUpdatedEpisode(Number hasUpdatedEpisode) {
		this.hasUpdatedEpisode = hasUpdatedEpisode;
		return this;
	}

	public Number getIsCollected(){
		return this.isCollected;
	}

	public MixInfo setIsCollected(Number isCollected) {
		this.isCollected = isCollected;
		return this;
	}

	public Number getStatus(){
		return this.status;
	}

	public MixInfo setStatus(Number status) {
		this.status = status;
		return this;
	}
}