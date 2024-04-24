package io.github.kloping.mywebsite.domain.bo.netEaseSongs;

public class Result {
	private Songs[] songs;
	private Number songCount;

	public Songs[] getSongs(){
		return this.songs;
	}

	public Result setSongs(Songs[] songs) {
		this.songs = songs;
		return this;
	}

	public Number getSongCount(){
		return this.songCount;
	}

	public Result setSongCount(Number songCount) {
		this.songCount = songCount;
		return this;
	}
}