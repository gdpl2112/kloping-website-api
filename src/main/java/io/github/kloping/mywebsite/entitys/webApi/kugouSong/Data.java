package io.github.kloping.mywebsite.entitys.webApi.kugouSong;

public class Data {
	private String author_name;
	private String audio_name;
	private String img;
	private String privilege2;
	private String play_url;
	private String song_name;
	private Number bitrate;
	private Number filesize;
	private Number privilege;
	private Boolean has_privilege;
	private Number timelength;
	private String recommend_album_id;
	private String audio_id;
	private Number have_album;
	private Number is_free_part;
	private String album_name;
	private Number album_id;
	private Number have_mv;
	private String author_id;
	private String lyrics;
	private String hash;
	private String play_backup_url;
	private Number video_id;
	private Authors[] authors;

	public String getAuthor_name(){
		return this.author_name;
	}

	public Data setAuthor_name(String author_name) {
		this.author_name = author_name;
		return this;
	}

	public String getAudio_name(){
		return this.audio_name;
	}

	public Data setAudio_name(String audio_name) {
		this.audio_name = audio_name;
		return this;
	}

	public String getImg(){
		return this.img;
	}

	public Data setImg(String img) {
		this.img = img;
		return this;
	}

	public String getPrivilege2(){
		return this.privilege2;
	}

	public Data setPrivilege2(String privilege2) {
		this.privilege2 = privilege2;
		return this;
	}

	public String getSong_name(){
		return this.song_name;
	}

	public Data setSong_name(String song_name) {
		this.song_name = song_name;
		return this;
	}

	public Number getBitrate(){
		return this.bitrate;
	}

	public Data setBitrate(Number bitrate) {
		this.bitrate = bitrate;
		return this;
	}

	public Number getFilesize(){
		return this.filesize;
	}

	public Data setFilesize(Number filesize) {
		this.filesize = filesize;
		return this;
	}

	public Number getPrivilege(){
		return this.privilege;
	}

	public Data setPrivilege(Number privilege) {
		this.privilege = privilege;
		return this;
	}

	public Boolean getHas_privilege(){
		return this.has_privilege;
	}

	public Data setHas_privilege(Boolean has_privilege) {
		this.has_privilege = has_privilege;
		return this;
	}

	public String getRecommend_album_id(){
		return this.recommend_album_id;
	}

	public Data setRecommend_album_id(String recommend_album_id) {
		this.recommend_album_id = recommend_album_id;
		return this;
	}

	public String getAudio_id(){
		return this.audio_id;
	}

	public Data setAudio_id(String audio_id) {
		this.audio_id = audio_id;
		return this;
	}

	public Number getHave_album(){
		return this.have_album;
	}

	public Data setHave_album(Number have_album) {
		this.have_album = have_album;
		return this;
	}

	public Number getIs_free_part(){
		return this.is_free_part;
	}

	public Data setIs_free_part(Number is_free_part) {
		this.is_free_part = is_free_part;
		return this;
	}

	public String getAlbum_name(){
		return this.album_name;
	}

	public Data setAlbum_name(String album_name) {
		this.album_name = album_name;
		return this;
	}

	public Number getHave_mv(){
		return this.have_mv;
	}

	public Data setHave_mv(Number have_mv) {
		this.have_mv = have_mv;
		return this;
	}

	public String getLyrics(){
		return this.lyrics;
	}

	public Data setLyrics(String lyrics) {
		this.lyrics = lyrics;
		return this;
	}

	public String getPlay_url(){
		return this.play_url;
	}

	public Data setPlay_url(String play_url) {
		this.play_url = play_url;
		return this;
	}

	public Number getTimelength(){
		return this.timelength;
	}

	public Data setTimelength(Number timelength) {
		this.timelength = timelength;
		return this;
	}

	public Number getAlbum_id(){
		return this.album_id;
	}

	public Data setAlbum_id(Number album_id) {
		this.album_id = album_id;
		return this;
	}

	public String getAuthor_id(){
		return this.author_id;
	}

	public Data setAuthor_id(String author_id) {
		this.author_id = author_id;
		return this;
	}

	public String getHash(){
		return this.hash;
	}

	public Data setHash(String hash) {
		this.hash = hash;
		return this;
	}

	public String getPlay_backup_url(){
		return this.play_backup_url;
	}

	public Data setPlay_backup_url(String play_backup_url) {
		this.play_backup_url = play_backup_url;
		return this;
	}

	public Number getVideo_id(){
		return this.video_id;
	}

	public Data setVideo_id(Number video_id) {
		this.video_id = video_id;
		return this;
	}

	public Authors[] getAuthors(){
		return this.authors;
	}

	public Data setAuthors(Authors[] authors) {
		this.authors = authors;
		return this;
	}
}