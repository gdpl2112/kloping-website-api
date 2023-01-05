package io.github.kloping.mywebsite.entitys.douyin;

public class Download_info {
	private String macLimit;
	private String macTime;
	private String limit;
	private String macApk;
	private String time;
	private String video;
	private String macVersion;
	private String apk;
	private String version;

	public String getMacLimit(){
		return this.macLimit;
	}

	public Download_info setMacLimit(String macLimit) {
		this.macLimit = macLimit;
		return this;
	}

	public String getMacTime(){
		return this.macTime;
	}

	public Download_info setMacTime(String macTime) {
		this.macTime = macTime;
		return this;
	}

	public String getLimit(){
		return this.limit;
	}

	public Download_info setLimit(String limit) {
		this.limit = limit;
		return this;
	}

	public String getMacApk(){
		return this.macApk;
	}

	public Download_info setMacApk(String macApk) {
		this.macApk = macApk;
		return this;
	}

	public String getTime(){
		return this.time;
	}

	public Download_info setTime(String time) {
		this.time = time;
		return this;
	}

	public String getVideo(){
		return this.video;
	}

	public Download_info setVideo(String video) {
		this.video = video;
		return this;
	}

	public String getMacVersion(){
		return this.macVersion;
	}

	public Download_info setMacVersion(String macVersion) {
		this.macVersion = macVersion;
		return this;
	}

	public String getApk(){
		return this.apk;
	}

	public Download_info setApk(String apk) {
		this.apk = apk;
		return this;
	}

	public String getVersion(){
		return this.version;
	}

	public Download_info setVersion(String version) {
		this.version = version;
		return this;
	}
}