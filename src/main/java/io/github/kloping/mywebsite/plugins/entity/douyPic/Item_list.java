package io.github.kloping.mywebsite.plugins.entity.douyPic;

public class Item_list {
	private Number is_preview;
	private Video video;
	private Number duration;
	private Music music;
	private Images[] images;
	private String forward_id;
	private Number create_time;
	private Author author;
	private Share_info share_info;
	private Image_infos[] image_infos;
	private Risk_infos risk_infos;
	private Cha_list[] cha_list;
	private String group_id_str;
	private String aweme_id;
	private Text_extra[] text_extra;
	private Number author_user_id;
	private Number group_id;
	private String share_url;
	private Boolean is_live_replay;
	private Number aweme_type;
	private String desc;
	private Statistics statistics;

	public Images[] getImages(){
		return this.images;
	}

	public Item_list setImages(Images[] images) {
		this.images = images;
		return this;
	}

	public String getForward_id(){
		return this.forward_id;
	}

	public Item_list setForward_id(String forward_id) {
		this.forward_id = forward_id;
		return this;
	}

	public Number getCreate_time(){
		return this.create_time;
	}

	public Item_list setCreate_time(Number create_time) {
		this.create_time = create_time;
		return this;
	}

	public Number getIs_preview(){
		return this.is_preview;
	}

	public Item_list setIs_preview(Number is_preview) {
		this.is_preview = is_preview;
		return this;
	}

	public Author getAuthor(){
		return this.author;
	}

	public Item_list setAuthor(Author author) {
		this.author = author;
		return this;
	}

	public Share_info getShare_info(){
		return this.share_info;
	}

	public Item_list setShare_info(Share_info share_info) {
		this.share_info = share_info;
		return this;
	}

	public Image_infos[] getImage_infos(){
		return this.image_infos;
	}

	public Item_list setImage_infos(Image_infos[] image_infos) {
		this.image_infos = image_infos;
		return this;
	}

	public Risk_infos getRisk_infos(){
		return this.risk_infos;
	}

	public Item_list setRisk_infos(Risk_infos risk_infos) {
		this.risk_infos = risk_infos;
		return this;
	}

	public Video getVideo(){
		return this.video;
	}

	public Item_list setVideo(Video video) {
		this.video = video;
		return this;
	}

	public Cha_list[] getCha_list(){
		return this.cha_list;
	}

	public Item_list setCha_list(Cha_list[] cha_list) {
		this.cha_list = cha_list;
		return this;
	}

	public Number getDuration(){
		return this.duration;
	}

	public Item_list setDuration(Number duration) {
		this.duration = duration;
		return this;
	}

	public Music getMusic(){
		return this.music;
	}

	public Item_list setMusic(Music music) {
		this.music = music;
		return this;
	}

	public String getGroup_id_str(){
		return this.group_id_str;
	}

	public Item_list setGroup_id_str(String group_id_str) {
		this.group_id_str = group_id_str;
		return this;
	}

	public String getAweme_id(){
		return this.aweme_id;
	}

	public Item_list setAweme_id(String aweme_id) {
		this.aweme_id = aweme_id;
		return this;
	}

	public Text_extra[] getText_extra(){
		return this.text_extra;
	}

	public Item_list setText_extra(Text_extra[] text_extra) {
		this.text_extra = text_extra;
		return this;
	}

	public Number getAuthor_user_id(){
		return this.author_user_id;
	}

	public Item_list setAuthor_user_id(Number author_user_id) {
		this.author_user_id = author_user_id;
		return this;
	}

	public Number getGroup_id(){
		return this.group_id;
	}

	public Item_list setGroup_id(Number group_id) {
		this.group_id = group_id;
		return this;
	}

	public String getShare_url(){
		return this.share_url;
	}

	public Item_list setShare_url(String share_url) {
		this.share_url = share_url;
		return this;
	}

	public Boolean getIs_live_replay(){
		return this.is_live_replay;
	}

	public Item_list setIs_live_replay(Boolean is_live_replay) {
		this.is_live_replay = is_live_replay;
		return this;
	}

	public Number getAweme_type(){
		return this.aweme_type;
	}

	public Item_list setAweme_type(Number aweme_type) {
		this.aweme_type = aweme_type;
		return this;
	}

	public String getDesc(){
		return this.desc;
	}

	public Item_list setDesc(String desc) {
		this.desc = desc;
		return this;
	}

	public Statistics getStatistics(){
		return this.statistics;
	}

	public Item_list setStatistics(Statistics statistics) {
		this.statistics = statistics;
		return this;
	}
}