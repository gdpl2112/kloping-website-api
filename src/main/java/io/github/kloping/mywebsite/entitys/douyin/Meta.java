package io.github.kloping.mywebsite.entitys.douyin;

public class Meta {
	private String qprf;
	private String loudness;
	private String peak;
	private String sr_score;

	public String getQprf(){
		return this.qprf;
	}

	public Meta setQprf(String qprf) {
		this.qprf = qprf;
		return this;
	}

	public String getLoudness(){
		return this.loudness;
	}

	public Meta setLoudness(String loudness) {
		this.loudness = loudness;
		return this;
	}

	public String getPeak(){
		return this.peak;
	}

	public Meta setPeak(String peak) {
		this.peak = peak;
		return this;
	}

	public String getSr_score(){
		return this.sr_score;
	}

	public Meta setSr_score(String sr_score) {
		this.sr_score = sr_score;
		return this;
	}
}