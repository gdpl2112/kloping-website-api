package io.github.kloping.mywebsite.plugins.entity.kugouDetail;

/**
 * @author github-kloping
 */
public class KugouSongDetail {
	private Number errcode;
	private Data data;
	private Black black;
	private String error;
	private Relative relative;
	private Number status;

	public Number getErrcode(){
		return this.errcode;
	}

	public KugouSongDetail setErrcode(Number errcode) {
		this.errcode = errcode;
		return this;
	}

	public Data getData(){
		return this.data;
	}

	public KugouSongDetail setData(Data data) {
		this.data = data;
		return this;
	}

	public Black getBlack(){
		return this.black;
	}

	public KugouSongDetail setBlack(Black black) {
		this.black = black;
		return this;
	}

	public String getError(){
		return this.error;
	}

	public KugouSongDetail setError(String error) {
		this.error = error;
		return this;
	}

	public Relative getRelative(){
		return this.relative;
	}

	public KugouSongDetail setRelative(Relative relative) {
		this.relative = relative;
		return this;
	}

	public Number getStatus(){
		return this.status;
	}

	public KugouSongDetail setStatus(Number status) {
		this.status = status;
		return this;
	}
}