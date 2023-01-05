package io.github.kloping.mywebsite.entitys.douyin;

public class Odin {
	private Number user_type;
	private String user_unique_id;
	private String user_id;
	private Number user_is_auth;

	public Number getUser_type(){
		return this.user_type;
	}

	public Odin setUser_type(Number user_type) {
		this.user_type = user_type;
		return this;
	}

	public String getUser_unique_id(){
		return this.user_unique_id;
	}

	public Odin setUser_unique_id(String user_unique_id) {
		this.user_unique_id = user_unique_id;
		return this;
	}

	public String getUser_id(){
		return this.user_id;
	}

	public Odin setUser_id(String user_id) {
		this.user_id = user_id;
		return this;
	}

	public Number getUser_is_auth(){
		return this.user_is_auth;
	}

	public Odin setUser_is_auth(Number user_is_auth) {
		this.user_is_auth = user_is_auth;
		return this;
	}
}