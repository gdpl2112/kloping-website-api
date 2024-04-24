package io.github.kloping.mywebsite.domain.bo.netEaseSongs;

public class Result {
	private NetSongs[] songs;
	private Number songCount;

	public NetSongs[] getSongs(){
		return this.songs;
	}

	public Result setSongs(NetSongs[] songs) {
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