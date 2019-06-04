package util;

public class Config1 {
	public static String CONFIG_FILE = "/WEB-INF/config.xml";
	private int interval;
	private int pagesize;
	private int timegap;
	private int minfilesize;
	private int maxfilesize;
	private boolean candelete;
	private String interval_desc;
	private String pagesize_desc;
	private String timegap_desc;
	private String minfilesize_desc;
	private String maxfilesize_desc;
	private String candelete_desc;

	public Config1() {
		this.interval_desc = "ָ��ɨ�迼����Ϣ�ļ��ʱ�䣬��λΪ ���ӡ�";
		this.interval = 30;
		this.pagesize_desc = "ָ����ҳ��ѯʱÿҳ��ʾ��¼����Ĭ��ֵ����ѯҳ���п��Ը��ģ���";
		this.pagesize = 30;
		this.timegap_desc = "ָ���ֹ���������ʱ����������ǰ������λΪ����";
		this.timegap = 15;
		this.minfilesize_desc = "ָ���ϴ��ļ��ĳ������ޣ��ֽڣ������ڴ�ֵ��������";
		this.minfilesize = 512;
		this.maxfilesize_desc = "ָ���ϴ��ļ��ĳ������ޣ��ֽڣ������ڴ�ֵ��������";
		this.maxfilesize = 131072;
		this.candelete_desc = "�����Ƿ������ʦ�Լ������ɾ��������Ϣ��";
		this.candelete = true;
	}

	public int getInterval() {
		return this.interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getPagesize() {
		return this.pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTimegap() {
		return this.timegap;
	}

	public void setTimegap(int timegap) {
		this.timegap = timegap;
	}

	public int getMinfilesize() {
		return this.minfilesize;
	}

	public void setMinfilesize(int minfilesize) {
		this.minfilesize = minfilesize;
	}

	public int getMaxfilesize() {
		return this.maxfilesize;
	}

	public void setMaxfilesize(int maxfilesize) {
		this.maxfilesize = maxfilesize;
	}

	public boolean isCandelete() {
		return this.candelete;
	}

	public void setCandelete(boolean candelete) {
		this.candelete = candelete;
	}

	public String getInterval_desc() {
		return this.interval_desc;
	}

	public void setInterval_desc(String interval_desc) {
		this.interval_desc = interval_desc;
	}

	public String getPagesize_desc() {
		return this.pagesize_desc;
	}

	public void setPagesize_desc(String pagesize_desc) {
		this.pagesize_desc = pagesize_desc;
	}

	public String getTimegap_desc() {
		return this.timegap_desc;
	}

	public void setTimegap_desc(String timegap_desc) {
		this.timegap_desc = timegap_desc;
	}

	public String getMinfilesize_desc() {
		return this.minfilesize_desc;
	}

	public void setMinfilesize_desc(String minfilesize_desc) {
		this.minfilesize_desc = minfilesize_desc;
	}

	public String getMaxfilesize_desc() {
		return this.maxfilesize_desc;
	}

	public void setMaxfilesize_desc(String maxfilesize_desc) {
		this.maxfilesize_desc = maxfilesize_desc;
	}

	public String getCandelete_desc() {
		return this.candelete_desc;
	}

	public void setCandelete_desc(String candelete_desc) {
		this.candelete_desc = candelete_desc;
	}
}
