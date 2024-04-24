package io.github.kloping.mywebsite.domain.bo.netEaseSongs;

public class MMusic {
	private String extension;
	private Number size;
	private Number volumeDelta;
	private Number bitrate;
	private Number playTime;
	private Number id;
	private Number dfsId;
	private Number sr;

	public String getExtension(){
		return this.extension;
	}

	public MMusic setExtension(String extension) {
		this.extension = extension;
		return this;
	}

	public Number getSize(){
		return this.size;
	}

	public MMusic setSize(Number size) {
		this.size = size;
		return this;
	}

	public Number getVolumeDelta(){
		return this.volumeDelta;
	}

	public MMusic setVolumeDelta(Number volumeDelta) {
		this.volumeDelta = volumeDelta;
		return this;
	}

	public Number getBitrate(){
		return this.bitrate;
	}

	public MMusic setBitrate(Number bitrate) {
		this.bitrate = bitrate;
		return this;
	}

	public Number getPlayTime(){
		return this.playTime;
	}

	public MMusic setPlayTime(Number playTime) {
		this.playTime = playTime;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public MMusic setId(Number id) {
		this.id = id;
		return this;
	}

	public Number getDfsId(){
		return this.dfsId;
	}

	public MMusic setDfsId(Number dfsId) {
		this.dfsId = dfsId;
		return this;
	}

	public Number getSr(){
		return this.sr;
	}

	public MMusic setSr(Number sr) {
		this.sr = sr;
		return this;
	}
}