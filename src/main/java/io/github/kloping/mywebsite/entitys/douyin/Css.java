package io.github.kloping.mywebsite.entitys.douyin;

public class Css {
	private Image image;
	private OuterContainer outerContainer;

	public Image getImage(){
		return this.image;
	}

	public Css setImage(Image image) {
		this.image = image;
		return this;
	}

	public OuterContainer getOuterContainer(){
		return this.outerContainer;
	}

	public Css setOuterContainer(OuterContainer outerContainer) {
		this.outerContainer = outerContainer;
		return this;
	}
}