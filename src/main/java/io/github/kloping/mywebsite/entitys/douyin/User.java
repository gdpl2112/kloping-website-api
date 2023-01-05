package io.github.kloping.mywebsite.entitys.douyin;

public class User {
	private Boolean isLogin;
	private Boolean isSpider;
	private Number statusCode;

	public Boolean getIsLogin(){
		return this.isLogin;
	}

	public User setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
		return this;
	}

	public Boolean getIsSpider(){
		return this.isSpider;
	}

	public User setIsSpider(Boolean isSpider) {
		this.isSpider = isSpider;
		return this;
	}

	public Number getStatusCode(){
		return this.statusCode;
	}

	public User setStatusCode(Number statusCode) {
		this.statusCode = statusCode;
		return this;
	}
}