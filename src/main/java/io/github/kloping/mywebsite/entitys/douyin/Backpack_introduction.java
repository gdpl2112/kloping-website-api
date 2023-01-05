package io.github.kloping.mywebsite.entitys.douyin;

public class Backpack_introduction {
	private Button[] button;
	private Text[] text;

	public Button[] getButton(){
		return this.button;
	}

	public Backpack_introduction setButton(Button[] button) {
		this.button = button;
		return this;
	}

	public Text[] getText(){
		return this.text;
	}

	public Backpack_introduction setText(Text[] text) {
		this.text = text;
		return this;
	}
}