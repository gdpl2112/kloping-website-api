package io.github.kloping.mywebsite.entitys.douyin;

public class OpenInSidebarCondition {
	private Number[] siteTypes;

	public Number[] getSiteTypes(){
		return this.siteTypes;
	}

	public OpenInSidebarCondition setSiteTypes(Number[] siteTypes) {
		this.siteTypes = siteTypes;
		return this;
	}
}