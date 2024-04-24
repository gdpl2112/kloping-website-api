package io.github.kloping.mywebsite.domain.bo.netEaseSongs;

public class NetEaseSongLyric {
	private Number code;
	private String lyric;
	private Number songStatus;
	private Number lyricVersion;

	public Number getCode(){
		return this.code;
	}

	public NetEaseSongLyric setCode(Number code) {
		this.code = code;
		return this;
	}

	public String getLyric(){
		return this.lyric;
	}

	public NetEaseSongLyric setLyric(String lyric) {
		this.lyric = lyric;
		return this;
	}

	public Number getSongStatus(){
		return this.songStatus;
	}

	public NetEaseSongLyric setSongStatus(Number songStatus) {
		this.songStatus = songStatus;
		return this;
	}

	public Number getLyricVersion(){
		return this.lyricVersion;
	}

	public NetEaseSongLyric setLyricVersion(Number lyricVersion) {
		this.lyricVersion = lyricVersion;
		return this;
	}
}