package io.github.kloping.mywebsite.plugins.entity.kugouDetail;

public class Data {
	private Number total;
	private Number istagresult;
	private String tab;
	private Number correctiontype;
	private Number forcecorrection;
	private String correctiontip;
	private Number istag;
	private Number allowerr;
	private Number timestamp;
	private Info[] info;

	public Number getTotal(){
		return this.total;
	}

	public Data setTotal(Number total) {
		this.total = total;
		return this;
	}

	public Number getIstagresult(){
		return this.istagresult;
	}

	public Data setIstagresult(Number istagresult) {
		this.istagresult = istagresult;
		return this;
	}

	public String getTab(){
		return this.tab;
	}

	public Data setTab(String tab) {
		this.tab = tab;
		return this;
	}

	public Number getCorrectiontype(){
		return this.correctiontype;
	}

	public Data setCorrectiontype(Number correctiontype) {
		this.correctiontype = correctiontype;
		return this;
	}

	public Number getForcecorrection(){
		return this.forcecorrection;
	}

	public Data setForcecorrection(Number forcecorrection) {
		this.forcecorrection = forcecorrection;
		return this;
	}

	public String getCorrectiontip(){
		return this.correctiontip;
	}

	public Data setCorrectiontip(String correctiontip) {
		this.correctiontip = correctiontip;
		return this;
	}

	public Number getIstag(){
		return this.istag;
	}

	public Data setIstag(Number istag) {
		this.istag = istag;
		return this;
	}

	public Number getAllowerr(){
		return this.allowerr;
	}

	public Data setAllowerr(Number allowerr) {
		this.allowerr = allowerr;
		return this;
	}

	public Number getTimestamp(){
		return this.timestamp;
	}

	public Data setTimestamp(Number timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public Info[] getInfo(){
		return this.info;
	}

	public Data setInfo(Info[] info) {
		this.info = info;
		return this;
	}
}