package io.github.kloping.mywebsite.entitys.webApi.qqOneSong;

public class Data {
	private Number verify_type;
	private String msg;
	private String testfilewifi;
	private String login_key;
	private Midurlinfo[] midurlinfo;
	private String[] thirdip;
	private String testfile2g;
	private Number expiration;
	private String uin;
	private String[] sip;
	private String servercheck;
	private Number retcode;

	public Number getVerify_type(){
		return this.verify_type;
	}

	public Data setVerify_type(Number verify_type) {
		this.verify_type = verify_type;
		return this;
	}

	public String getMsg(){
		return this.msg;
	}

	public Data setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public String getLogin_key(){
		return this.login_key;
	}

	public Data setLogin_key(String login_key) {
		this.login_key = login_key;
		return this;
	}

	public Midurlinfo[] getMidurlinfo(){
		return this.midurlinfo;
	}

	public Data setMidurlinfo(Midurlinfo[] midurlinfo) {
		this.midurlinfo = midurlinfo;
		return this;
	}

	public String[] getThirdip(){
		return this.thirdip;
	}

	public Data setThirdip(String[] thirdip) {
		this.thirdip = thirdip;
		return this;
	}

	public String getTestfile2g(){
		return this.testfile2g;
	}

	public Data setTestfile2g(String testfile2g) {
		this.testfile2g = testfile2g;
		return this;
	}

	public String getTestfilewifi(){
		return this.testfilewifi;
	}

	public Data setTestfilewifi(String testfilewifi) {
		this.testfilewifi = testfilewifi;
		return this;
	}

	public Number getExpiration(){
		return this.expiration;
	}

	public Data setExpiration(Number expiration) {
		this.expiration = expiration;
		return this;
	}

	public String getUin(){
		return this.uin;
	}

	public Data setUin(String uin) {
		this.uin = uin;
		return this;
	}

	public String[] getSip(){
		return this.sip;
	}

	public Data setSip(String[] sip) {
		this.sip = sip;
		return this;
	}

	public String getServercheck(){
		return this.servercheck;
	}

	public Data setServercheck(String servercheck) {
		this.servercheck = servercheck;
		return this;
	}

	public Number getRetcode(){
		return this.retcode;
	}

	public Data setRetcode(Number retcode) {
		this.retcode = retcode;
		return this;
	}
}