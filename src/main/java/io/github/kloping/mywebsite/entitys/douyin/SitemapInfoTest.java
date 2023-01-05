package io.github.kloping.mywebsite.entitys.douyin;

public class SitemapInfoTest {
	private String entityTitle;
	private Number total;
	private Number entityType;
	private String entitySubType;
	private String entityDesc;
	private String href;

	public String getEntityTitle(){
		return this.entityTitle;
	}

	public SitemapInfoTest setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
		return this;
	}

	public Number getTotal(){
		return this.total;
	}

	public SitemapInfoTest setTotal(Number total) {
		this.total = total;
		return this;
	}

	public Number getEntityType(){
		return this.entityType;
	}

	public SitemapInfoTest setEntityType(Number entityType) {
		this.entityType = entityType;
		return this;
	}

	public String getEntitySubType(){
		return this.entitySubType;
	}

	public SitemapInfoTest setEntitySubType(String entitySubType) {
		this.entitySubType = entitySubType;
		return this;
	}

	public String getEntityDesc(){
		return this.entityDesc;
	}

	public SitemapInfoTest setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
		return this;
	}

	public String getHref(){
		return this.href;
	}

	public SitemapInfoTest setHref(String href) {
		this.href = href;
		return this;
	}
}