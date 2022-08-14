package io.github.kloping.mywebsite.entitys.webApi.fcgPlaySingleSong;

public class Volume {
	private Number lra;
	private Number peak;
	private Number gain;

	public Number getLra(){
		return this.lra;
	}

	public Volume setLra(Number lra) {
		this.lra = lra;
		return this;
	}

	public Number getPeak(){
		return this.peak;
	}

	public Volume setPeak(Number peak) {
		this.peak = peak;
		return this;
	}

	public Number getGain(){
		return this.gain;
	}

	public Volume setGain(Number gain) {
		this.gain = gain;
		return this;
	}
}