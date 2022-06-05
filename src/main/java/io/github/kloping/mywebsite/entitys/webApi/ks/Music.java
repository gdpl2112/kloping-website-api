package io.github.kloping.mywebsite.entitys.webApi.ks;

public class Music {
	private Number genreId;
	private AudioUrls[] audioUrls;
	private AvatarUrls[] avatarUrls;
	private String artist;
	private Number audioType;
	private Number loudness;
	private Number photoId;
	private Number type;
	private Boolean disableEnhancedEntry;
	private ImageUrls[] imageUrls;
	private String name;
	private Number id;
	private User user;

	public Number getGenreId(){
		return this.genreId;
	}

	public Music setGenreId(Number genreId) {
		this.genreId = genreId;
		return this;
	}

	public AudioUrls[] getAudioUrls(){
		return this.audioUrls;
	}

	public Music setAudioUrls(AudioUrls[] audioUrls) {
		this.audioUrls = audioUrls;
		return this;
	}

	public AvatarUrls[] getAvatarUrls(){
		return this.avatarUrls;
	}

	public Music setAvatarUrls(AvatarUrls[] avatarUrls) {
		this.avatarUrls = avatarUrls;
		return this;
	}

	public String getArtist(){
		return this.artist;
	}

	public Music setArtist(String artist) {
		this.artist = artist;
		return this;
	}

	public Number getLoudness(){
		return this.loudness;
	}

	public Music setLoudness(Number loudness) {
		this.loudness = loudness;
		return this;
	}

	public Number getAudioType(){
		return this.audioType;
	}

	public Music setAudioType(Number audioType) {
		this.audioType = audioType;
		return this;
	}

	public Number getPhotoId(){
		return this.photoId;
	}

	public Music setPhotoId(Number photoId) {
		this.photoId = photoId;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public Music setType(Number type) {
		this.type = type;
		return this;
	}

	public Boolean getDisableEnhancedEntry(){
		return this.disableEnhancedEntry;
	}

	public Music setDisableEnhancedEntry(Boolean disableEnhancedEntry) {
		this.disableEnhancedEntry = disableEnhancedEntry;
		return this;
	}

	public ImageUrls[] getImageUrls(){
		return this.imageUrls;
	}

	public Music setImageUrls(ImageUrls[] imageUrls) {
		this.imageUrls = imageUrls;
		return this;
	}

	public String getName(){
		return this.name;
	}

	public Music setName(String name) {
		this.name = name;
		return this;
	}

	public Number getId(){
		return this.id;
	}

	public Music setId(Number id) {
		this.id = id;
		return this;
	}

	public User getUser(){
		return this.user;
	}

	public Music setUser(User user) {
		this.user = user;
		return this;
	}
}