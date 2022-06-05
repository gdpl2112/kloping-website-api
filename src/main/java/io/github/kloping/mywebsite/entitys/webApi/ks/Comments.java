package io.github.kloping.mywebsite.entitys.webApi.ks;

public class Comments {
	private String author_name;
	private CashTags cashTags;
	private Number photo_id;
	private String headurl;
	private Headurls[] headurls;
	private Boolean author_liked;
	private Number type;
	private Number comment_id;
	private Boolean authorVerified;
	private String content;
	private String user_sex;
	private Number reply_to;
	private Number user_id;
	private String time;
	private Number author_id;
	private Number timestamp;

	public String getAuthor_name(){
		return this.author_name;
	}

	public Comments setAuthor_name(String author_name) {
		this.author_name = author_name;
		return this;
	}

	public CashTags getCashTags(){
		return this.cashTags;
	}

	public Comments setCashTags(CashTags cashTags) {
		this.cashTags = cashTags;
		return this;
	}

	public Number getPhoto_id(){
		return this.photo_id;
	}

	public Comments setPhoto_id(Number photo_id) {
		this.photo_id = photo_id;
		return this;
	}

	public String getHeadurl(){
		return this.headurl;
	}

	public Comments setHeadurl(String headurl) {
		this.headurl = headurl;
		return this;
	}

	public Headurls[] getHeadurls(){
		return this.headurls;
	}

	public Comments setHeadurls(Headurls[] headurls) {
		this.headurls = headurls;
		return this;
	}

	public Boolean getAuthor_liked(){
		return this.author_liked;
	}

	public Comments setAuthor_liked(Boolean author_liked) {
		this.author_liked = author_liked;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public Comments setType(Number type) {
		this.type = type;
		return this;
	}

	public Number getComment_id(){
		return this.comment_id;
	}

	public Comments setComment_id(Number comment_id) {
		this.comment_id = comment_id;
		return this;
	}

	public Boolean getAuthorVerified(){
		return this.authorVerified;
	}

	public Comments setAuthorVerified(Boolean authorVerified) {
		this.authorVerified = authorVerified;
		return this;
	}

	public String getContent(){
		return this.content;
	}

	public Comments setContent(String content) {
		this.content = content;
		return this;
	}

	public String getUser_sex(){
		return this.user_sex;
	}

	public Comments setUser_sex(String user_sex) {
		this.user_sex = user_sex;
		return this;
	}

	public Number getReply_to(){
		return this.reply_to;
	}

	public Comments setReply_to(Number reply_to) {
		this.reply_to = reply_to;
		return this;
	}

	public Number getUser_id(){
		return this.user_id;
	}

	public Comments setUser_id(Number user_id) {
		this.user_id = user_id;
		return this;
	}

	public String getTime(){
		return this.time;
	}

	public Comments setTime(String time) {
		this.time = time;
		return this;
	}

	public Number getAuthor_id(){
		return this.author_id;
	}

	public Comments setAuthor_id(Number author_id) {
		this.author_id = author_id;
		return this;
	}

	public Number getTimestamp(){
		return this.timestamp;
	}

	public Comments setTimestamp(Number timestamp) {
		this.timestamp = timestamp;
		return this;
	}
}