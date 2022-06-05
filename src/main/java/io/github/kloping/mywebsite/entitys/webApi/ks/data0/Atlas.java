package io.github.kloping.mywebsite.entitys.webApi.ks.data0;

public class Atlas {
	private Number volume;
	private String music;
	private CdnList[] cdnList;
	private Size[] size;
	private Number type;
	private String[] cdn;
	private String[] list;

	public Number getVolume(){
		return this.volume;
	}

	public Atlas setVolume(Number volume) {
		this.volume = volume;
		return this;
	}

	public String getMusic(){
		return this.music;
	}

	public Atlas setMusic(String music) {
		this.music = music;
		return this;
	}

	public CdnList[] getCdnList(){
		return this.cdnList;
	}

	public Atlas setCdnList(CdnList[] cdnList) {
		this.cdnList = cdnList;
		return this;
	}

	public Size[] getSize(){
		return this.size;
	}

	public Atlas setSize(Size[] size) {
		this.size = size;
		return this;
	}

	public Number getType(){
		return this.type;
	}

	public Atlas setType(Number type) {
		this.type = type;
		return this;
	}

	public String[] getCdn(){
		return this.cdn;
	}

	public Atlas setCdn(String[] cdn) {
		this.cdn = cdn;
		return this;
	}

	public String[] getList(){
		return this.list;
	}

	public Atlas setList(String[] list) {
		this.list = list;
		return this;
	}
}