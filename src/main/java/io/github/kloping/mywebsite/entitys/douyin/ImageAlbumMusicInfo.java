package io.github.kloping.mywebsite.entitys.douyin;

public class ImageAlbumMusicInfo {
	private Number volume;
	private Number beginTime;
	private Number endTime;

	public Number getVolume(){
		return this.volume;
	}

	public ImageAlbumMusicInfo setVolume(Number volume) {
		this.volume = volume;
		return this;
	}

	public Number getBeginTime(){
		return this.beginTime;
	}

	public ImageAlbumMusicInfo setBeginTime(Number beginTime) {
		this.beginTime = beginTime;
		return this;
	}

	public Number getEndTime(){
		return this.endTime;
	}

	public ImageAlbumMusicInfo setEndTime(Number endTime) {
		this.endTime = endTime;
		return this;
	}
}