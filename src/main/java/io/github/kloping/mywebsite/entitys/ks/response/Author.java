package io.github.kloping.mywebsite.entitys.ks.response;

public class Author {
	private Boolean following;
	private String __typename;
	private String name;
	private String id;
	private String headerUrl;

	public Boolean getFollowing(){
		return this.following;
	}

	public Author setFollowing(Boolean following) {
		this.following = following;
		return this;
	}

	public String get__typename(){
		return this.__typename;
	}

	public Author set__typename(String __typename) {
		this.__typename = __typename;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Author setName(String name) {
		this.name = name;
		return this;
	}

	public String getId(){
		return this.id;
	}

	public Author setId(String id) {
		this.id = id;
		return this;
	}

	public String getHeaderUrl(){
		return this.headerUrl;
	}

	public Author setHeaderUrl(String headerUrl) {
		this.headerUrl = headerUrl;
		return this;
	}
}