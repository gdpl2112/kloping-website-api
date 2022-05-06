package io.github.kloping.mywebsite.entitys.webApi.tencentVideo;

/**
 * @author github-kloping
 */
public class DataResponse {
	private Number ret;
	private String msg;
	private Data data;

	public Number getRet(){
		return this.ret;
	}

	public DataResponse setRet(Number ret) {
		this.ret = ret;
		return this;
	}

	public String getMsg(){
		return this.msg;
	}

	public DataResponse setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Data getData(){
		return this.data;
	}

	public DataResponse setData(Data data) {
		this.data = data;
		return this;
	}
}