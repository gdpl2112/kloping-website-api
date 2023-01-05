package io.github.kloping.mywebsite.entitys.douyin;

public class Ad_config {
	private OpenInSidebarCondition openInSidebarCondition;

	public OpenInSidebarCondition getOpenInSidebarCondition(){
		return this.openInSidebarCondition;
	}

	public Ad_config setOpenInSidebarCondition(OpenInSidebarCondition openInSidebarCondition) {
		this.openInSidebarCondition = openInSidebarCondition;
		return this;
	}
}