package io.github.kloping.mywebsite.domain.bo.netEaseSongs;

public class NetEaseSongs {
	private Result result;
	private Number code;

	public Result getResult(){
		return this.result;
	}

	public NetEaseSongs setResult(Result result) {
		this.result = result;
		return this;
	}

	public Number getCode(){
		return this.code;
	}

	public NetEaseSongs setCode(Number code) {
		this.code = code;
		return this;
	}
}