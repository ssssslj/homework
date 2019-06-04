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
			addActionError("两次输入的密码不一致");
			return SUCCESS;
		}
		if (!EncryptUtil.checkPassword(oldPassword, teacher.getPassword())) {
			addActionError("输入的旧密码不正确");
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
		config.setInterval_desc("指定扫描考试信息的间隔时间，单位为 分钟。");
		config.setPagesize(Integer.parseInt(request.getParameter("pagesize")));
		
		PageBean.PAGE_SIZE = Integer.parseInt(request.getParameter("pagesize"));
		
		config.setPagesize_desc("指定分页查询时每页显示记录数的默认值（查询页面中可以更改）。");
		config.setTimegap(Integer.parseInt(request.getParameter("timegap")));
		config.setTimegap_desc("指定手工开启考试时允许的最大提前量，单位为分钟");
		config.setMinfilesize(Integer.parseInt(request.getParameter("minfilesize")));
		config.setMinfilesize_desc("指定上传文件的长度下限（字节），低于此值发出警告");
		config.setMaxfilesize(Integer.parseInt(request.getParameter("maxfilesize")));
		config.setMaxfilesize_desc("指定上传文件的长度上限（字节），高于此值发出警告");
		boolean flag = false;
		if (request.getParameter("candelete")!=null&&request.getParameter("candelete").equals("on")) {
			flag = true;
			// System.out.println(request.getParameter("candelete"));
		}
		config.setCandelete(flag);
		config.setCandelete_desc("设置是否允许教师自己清理和删除考试信息。");
		
		
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
