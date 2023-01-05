package io.github.kloping.mywebsite.entitys.douyin;

public class YiqingPageConfig {
	private ServiceList[] serviceList;
	private Boolean open;

	public ServiceList[] getServiceList(){
		return this.serviceList;
	}

	public YiqingPageConfig setServiceList(ServiceList[] serviceList) {
		this.serviceList = serviceList;
		return this;
	}

	public Boolean getOpen(){
		return this.open;
	}

	public YiqingPageConfig setOpen(Boolean open) {
		this.open = open;
		return this;
	}
}