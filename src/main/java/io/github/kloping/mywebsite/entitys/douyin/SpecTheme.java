package io.github.kloping.mywebsite.entitys.douyin;

public class SpecTheme {
	private Boolean themeSwitch;
	private String headerDark;
	private String bgLight;
	private String siderDark;
	private String siderLight;
	private String headerLight;
	private Boolean themeFurtherSwitch;
	private String bgDark;

	public Boolean getThemeSwitch(){
		return this.themeSwitch;
	}

	public SpecTheme setThemeSwitch(Boolean themeSwitch) {
		this.themeSwitch = themeSwitch;
		return this;
	}

	public String getHeaderDark(){
		return this.headerDark;
	}

	public SpecTheme setHeaderDark(String headerDark) {
		this.headerDark = headerDark;
		return this;
	}

	public String getBgLight(){
		return this.bgLight;
	}

	public SpecTheme setBgLight(String bgLight) {
		this.bgLight = bgLight;
		return this;
	}

	public String getSiderDark(){
		return this.siderDark;
	}

	public SpecTheme setSiderDark(String siderDark) {
		this.siderDark = siderDark;
		return this;
	}

	public String getSiderLight(){
		return this.siderLight;
	}

	public SpecTheme setSiderLight(String siderLight) {
		this.siderLight = siderLight;
		return this;
	}

	public String getHeaderLight(){
		return this.headerLight;
	}

	public SpecTheme setHeaderLight(String headerLight) {
		this.headerLight = headerLight;
		return this;
	}

	public Boolean getThemeFurtherSwitch(){
		return this.themeFurtherSwitch;
	}

	public SpecTheme setThemeFurtherSwitch(Boolean themeFurtherSwitch) {
		this.themeFurtherSwitch = themeFurtherSwitch;
		return this;
	}

	public String getBgDark(){
		return this.bgDark;
	}

	public SpecTheme setBgDark(String bgDark) {
		this.bgDark = bgDark;
		return this;
	}
}