package io.github.kloping.mywebsite.entitys.webApi.pcwiqiyi;

public class People {
	private Producer[] producer;
	private Main_charactor[] main_charactor;

	public Producer[] getProducer(){
		return this.producer;
	}

	public People setProducer(Producer[] producer) {
		this.producer = producer;
		return this;
	}

	public Main_charactor[] getMain_charactor(){
		return this.main_charactor;
	}

	public People setMain_charactor(Main_charactor[] main_charactor) {
		this.main_charactor = main_charactor;
		return this;
	}
}