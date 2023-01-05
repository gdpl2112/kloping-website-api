package io.github.kloping.mywebsite.entitys.douyin;

public class DefaultUserPost {
	private Res res;
	private Param param;
	private Object nextVideoInfo;

	public Res getRes(){
		return this.res;
	}

	public DefaultUserPost setRes(Res res) {
		this.res = res;
		return this;
	}

	public Param getParam(){
		return this.param;
	}

	public DefaultUserPost setParam(Param param) {
		this.param = param;
		return this;
	}

	public Object getNextVideoInfo(){
		return this.nextVideoInfo;
	}

	public DefaultUserPost setNextVideoInfo(Object nextVideoInfo) {
		this.nextVideoInfo = nextVideoInfo;
		return this;
	}
}