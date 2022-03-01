package io.github.kloping.mywebsite.entitys.webApi.ks.response;

public class Feeds {
	private String currentPcursor;
	private Author author;
	private String __typename;
	private Photo photo;
	private String llsid;
	private Number canAddComment;
	private Number type;
	private Tags[] tags;
	private Number status;

	public String getCurrentPcursor(){
		return this.currentPcursor;
	}

	public Feeds setCurrentPcursor(String currentPcursor) {
		this.currentPcursor = currentPcursor;
		return this;
	}

	public Author getAuthor(){
		return this.author;
	}

	public Feeds setAuthor(Author author) {
		this.author = author;
		return this;
	}

	public String get__typename(){
		return this.__typename;
	}

	public Feeds set__typename(String __typename) {
		this.__typename = __typename;
		return this;
	}

	public Photo getPhoto(){
		return this.photo;
	}

	public Feeds setPhoto(Photo photo) {
		this.photo = photo;
		return this;
	}

	public String getLlsid(){
		return this.llsid;
	}

	public Feeds setLlsid(String llsid) {
		this.llsid = llsid;
		return this;
	}

	public Number getCanAddComment(){
		return this.canAddComment;
	}

	public Feeds setCanAddComment(Number canAddComment) {
		this.canAddComment = canAddComment;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public Feeds setType(Number type) {
		this.type = type;
		return this;
	}

	public Tags[] getTags(){
		return this.tags;
	}

	public Feeds setTags(Tags[] tags) {
		this.tags = tags;
		return this;
	}

	public Number getStatus(){
		return this.status;
	}

	public Feeds setStatus(Number status) {
		this.status = status;
		return this;
	}
}