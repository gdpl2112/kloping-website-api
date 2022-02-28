package io.github.kloping.mywebsite.plugins.entity.qqDetail;

public class Preview {
	private Number tryend;
	private Number trybegin;
	private Number trysize;

	public Number getTryend(){
		return this.tryend;
	}

	public Preview setTryend(Number tryend) {
		this.tryend = tryend;
		return this;
	}

	public Number getTrybegin(){
		return this.trybegin;
	}

	public Preview setTrybegin(Number trybegin) {
		this.trybegin = trybegin;
		return this;
	}

	public Number getTrysize(){
		return this.trysize;
	}

	public Preview setTrysize(Number trysize) {
		this.trysize = trysize;
		return this;
	}
}