package io.github.kloping.mywebsite.entitys.webApi.ks;

public class ShareInfo {
	private String groupName;
	private String shareTitle;
	private String docId;
	private String shareId;
	private String shareType;
	private String shareSubTitle;

	public String getGroupName(){
		return this.groupName;
	}

	public ShareInfo setGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}

	public String getShareTitle(){
		return this.shareTitle;
	}

	public ShareInfo setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
		return this;
	}

	public String getDocId(){
		return this.docId;
	}

	public ShareInfo setDocId(String docId) {
		this.docId = docId;
		return this;
	}

	public String getShareId(){
		return this.shareId;
	}

	public ShareInfo setShareId(String shareId) {
		this.shareId = shareId;
		return this;
	}

	public String getShareType(){
		return this.shareType;
	}

	public ShareInfo setShareType(String shareType) {
		this.shareType = shareType;
		return this;
	}

	public String getShareSubTitle(){
		return this.shareSubTitle;
	}

	public ShareInfo setShareSubTitle(String shareSubTitle) {
		this.shareSubTitle = shareSubTitle;
		return this;
	}
}