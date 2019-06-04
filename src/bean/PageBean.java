package bean;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import util.Config1;
import util.ConfigUtil;

public class PageBean<T> {

	public static int PAGE_SIZE;
	
	static {
		
		Config1 config=new Config1();
		 String configPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("") + Config1.CONFIG_FILE;
		File file=new File(configPath);
		if(ServletActionContext.getRequest().getSession().getAttribute("cfg")==null)
		{
			ConfigUtil.writeConfig(file, config);
		}
		config=ConfigUtil.parseConfig(file);
		PAGE_SIZE = config.getPagesize();
	}
	
	private int page;   //��ǰҳ��
    private int totalCount;   //�ܼ�¼��
    private int totalPage;    //��ҳ��
    private int pageSize = PAGE_SIZE;        //ÿҳ��ʾ�ļ�¼��
    private List<T> list;     //ÿҳ��ʾ���ݵļ���
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

    
    
    
}
