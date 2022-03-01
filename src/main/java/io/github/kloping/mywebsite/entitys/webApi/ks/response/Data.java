package io.github.kloping.mywebsite.entitys.webApi.ks.response;

public class Data {
	private VisionSearchPhoto visionSearchPhoto;

	public VisionSearchPhoto getVisionSearchPhoto(){
		return this.visionSearchPhoto;
	}

	public Data setVisionSearchPhoto(VisionSearchPhoto visionSearchPhoto) {
		this.visionSearchPhoto = visionSearchPhoto;
		return this;
	}
}