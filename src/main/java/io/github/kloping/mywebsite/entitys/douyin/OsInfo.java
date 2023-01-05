package io.github.kloping.mywebsite.entitys.douyin;

public class OsInfo {
	private String os;
	private String version;

	public String getOs(){
		return this.os;
	}

	public OsInfo setOs(String os) {
		this.os = os;
		return this;
	}

	public String getVersion(){
		return this.version;
	}

	public OsInfo setVersion(String version) {
		this.version = version;
		return this;
	}
}