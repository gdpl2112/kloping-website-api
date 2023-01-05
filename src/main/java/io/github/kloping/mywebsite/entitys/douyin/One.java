package io.github.kloping.mywebsite.entitys.douyin;

public class One {
	private String envService;
	private AbTestData abTestData;
	private BackendAbTest backendAbTest;
	private String landingPage;
	private Odin odin;
	private Boolean isClient;
	private Boolean isSpider;
	private String ua;
	private String pathname;
	private Number ttwidCreateTime;
	private AbFormatData abFormatData;
	private TccConfig tccConfig;
	private OsInfo osInfo;
	private Number serverTime;
	private String logId;
	private User user;

	public String getEnvService(){
		return this.envService;
	}

	public One setEnvService(String envService) {
		this.envService = envService;
		return this;
	}

	public AbTestData getAbTestData(){
		return this.abTestData;
	}

	public One setAbTestData(AbTestData abTestData) {
		this.abTestData = abTestData;
		return this;
	}

	public BackendAbTest getBackendAbTest(){
		return this.backendAbTest;
	}

	public One setBackendAbTest(BackendAbTest backendAbTest) {
		this.backendAbTest = backendAbTest;
		return this;
	}

	public String getLandingPage(){
		return this.landingPage;
	}

	public One setLandingPage(String landingPage) {
		this.landingPage = landingPage;
		return this;
	}

	public Odin getOdin(){
		return this.odin;
	}

	public One setOdin(Odin odin) {
		this.odin = odin;
		return this;
	}

	public Boolean getIsClient(){
		return this.isClient;
	}

	public One setIsClient(Boolean isClient) {
		this.isClient = isClient;
		return this;
	}

	public Boolean getIsSpider(){
		return this.isSpider;
	}

	public One setIsSpider(Boolean isSpider) {
		this.isSpider = isSpider;
		return this;
	}

	public String getUa(){
		return this.ua;
	}

	public One setUa(String ua) {
		this.ua = ua;
		return this;
	}

	public String getPathname(){
		return this.pathname;
	}

	public One setPathname(String pathname) {
		this.pathname = pathname;
		return this;
	}

	public Number getTtwidCreateTime(){
		return this.ttwidCreateTime;
	}

	public One setTtwidCreateTime(Number ttwidCreateTime) {
		this.ttwidCreateTime = ttwidCreateTime;
		return this;
	}

	public AbFormatData getAbFormatData(){
		return this.abFormatData;
	}

	public One setAbFormatData(AbFormatData abFormatData) {
		this.abFormatData = abFormatData;
		return this;
	}

	public TccConfig getTccConfig(){
		return this.tccConfig;
	}

	public One setTccConfig(TccConfig tccConfig) {
		this.tccConfig = tccConfig;
		return this;
	}

	public OsInfo getOsInfo(){
		return this.osInfo;
	}

	public One setOsInfo(OsInfo osInfo) {
		this.osInfo = osInfo;
		return this;
	}

	public Number getServerTime(){
		return this.serverTime;
	}

	public One setServerTime(Number serverTime) {
		this.serverTime = serverTime;
		return this;
	}

	public String getLogId(){
		return this.logId;
	}

	public One setLogId(String logId) {
		this.logId = logId;
		return this;
	}

	public User getUser(){
		return this.user;
	}

	public One setUser(User user) {
		this.user = user;
		return this;
	}
}