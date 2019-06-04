package action;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import bean.PageBean;
import util.Config1;
import util.ConfigUtil;
import util.EncryptUtil;
import vo.Teacher;

public class AdminConfigAction extends BaseAction {

	private String oldPassword;
	private String newPassword;
	private String newPassword2;
	
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
	
	
	public String resetPassword() {
		
		Teacher teacher = tManager.getTeacher((Integer)getSession().get("adminId"));
		if (!newPassword.equals(newPassword2)) {
			addActionError("������������벻һ��");
			return SUCCESS;
		}
		if (!EncryptUtil.checkPassword(oldPassword, teacher.getPassword())) {
			addActionError("����ľ����벻��ȷ");
			return SUCCESS;
		}
		
		teacher.setPassword(EncryptUtil.Encoder(newPassword));
		tManager.updateTeacher(teacher);
		
		return SUCCESS;
	}
	public String adminConfigPage() {
		Config1 config=new Config1();
		 String configPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("") + Config1.CONFIG_FILE;
		File file=new File(configPath);
		if(!file.exists())
		{
			ConfigUtil.writeConfig(file, config);
		}
		config=ConfigUtil.parseConfig(file);
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("cfg", config);
		return SUCCESS;

	}

	public String setadminConfigPage() {
		Config1 config = new Config1();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		config.setInterval(Integer.parseInt(request.getParameter("interval")));
		config.setInterval_desc("ָ��ɨ�迼����Ϣ�ļ��ʱ�䣬��λΪ ���ӡ�");
		config.setPagesize(Integer.parseInt(request.getParameter("pagesize")));
		
		PageBean.PAGE_SIZE = Integer.parseInt(request.getParameter("pagesize"));
		
		config.setPagesize_desc("ָ����ҳ��ѯʱÿҳ��ʾ��¼����Ĭ��ֵ����ѯҳ���п��Ը��ģ���");
		config.setTimegap(Integer.parseInt(request.getParameter("timegap")));
		config.setTimegap_desc("ָ���ֹ���������ʱ����������ǰ������λΪ����");
		config.setMinfilesize(Integer.parseInt(request.getParameter("minfilesize")));
		config.setMinfilesize_desc("ָ���ϴ��ļ��ĳ������ޣ��ֽڣ������ڴ�ֵ��������");
		config.setMaxfilesize(Integer.parseInt(request.getParameter("maxfilesize")));
		config.setMaxfilesize_desc("ָ���ϴ��ļ��ĳ������ޣ��ֽڣ������ڴ�ֵ��������");
		boolean flag = false;
		if (request.getParameter("candelete")!=null&&request.getParameter("candelete").equals("on")) {
			flag = true;
			// System.out.println(request.getParameter("candelete"));
		}
		config.setCandelete(flag);
		config.setCandelete_desc("�����Ƿ������ʦ�Լ������ɾ��������Ϣ��");
		
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("cfg", config);
		 String configPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("") + config.CONFIG_FILE;
		File file=new File(configPath);
        ConfigUtil.writeConfig(file, config);
        
        Config1 cf=ConfigUtil.parseConfig(file);
        getApplication().put("config", cf);
        System.out.println(cf.getInterval());
		return SUCCESS;
	}
}
