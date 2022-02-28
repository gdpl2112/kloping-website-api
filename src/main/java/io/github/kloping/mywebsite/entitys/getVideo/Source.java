package io.github.kloping.mywebsite.entitys.getVideo;

public class Source {
	private Eps[] eps;

	public Eps[] getEps(){
		return this.eps;
	}

	public Source setEps(Eps[] eps) {
		this.eps = eps;
		return this;
	}
}