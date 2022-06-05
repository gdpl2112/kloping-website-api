package io.github.kloping.mywebsite.entitys.webApi.ks;

public class User {
	private String user_sex;
	private String eid;
	private Number user_id;
	private String user_name;
	private Boolean following;
	private String headurl;
	private Headurls[] headurls;
	private Boolean visitorBeFollowed;
	private String kwaiId;

	public String getUser_sex(){
		return this.user_sex;
	}

	public User setUser_sex(String user_sex) {
		this.user_sex = user_sex;
		return this;
	}

	public String getEid(){
		return this.eid;
	}

	public User setEid(String eid) {
		this.eid = eid;
		return this;
	}

	public Number getUser_id(){
		return this.user_id;
	}

	public User setUser_id(Number user_id) {
		this.user_id = user_id;
		return this;
	}

	public String getUser_name(){
		return this.user_name;
	}

	public User setUser_name(String user_name) {
		this.user_name = user_name;
		return this;
	}

	public Boolean getFollowing(){
		return this.following;
	}

	public User setFollowing(Boolean following) {
		this.following = following;
		return this;
	}

	public String getHeadurl(){
		return this.headurl;
	}

	public User setHeadurl(String headurl) {
		this.headurl = headurl;
		return this;
	}

	public Headurls[] getHeadurls(){
		return this.headurls;
	}

	public User setHeadurls(Headurls[] headurls) {
		this.headurls = headurls;
		return this;
	}

	public Boolean getVisitorBeFollowed(){
		return this.visitorBeFollowed;
	}

	public User setVisitorBeFollowed(Boolean visitorBeFollowed) {
		this.visitorBeFollowed = visitorBeFollowed;
		return this;
	}

	public String getKwaiId(){
		return this.kwaiId;
	}

	public User setKwaiId(String kwaiId) {
		this.kwaiId = kwaiId;
		return this;
	}
}