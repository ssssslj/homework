package action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import util.EncryptUtil;
import vo.Teacher;

/**
 * 
 */
public class TeacherLoginAction extends BaseAction {

	private String number;
	private String password;
	
	private String oldPassword;
	private String newPassword;
	private String newPassword2;
   
    public TeacherLoginAction() {
    	
    }

    
    
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



	public String getNumber() {
		return number;
	}



	public void setNumber(String number) {
		this.number = number;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	/**
     * 
     */
    public String loginTeacher() {
        Teacher teacher = new Teacher();
        teacher.setNumber(this.number);
        teacher.setPassword(this.password);
        System.out.println(EncryptUtil.Encoder(this.password));
        int tid = super.tManager.checkTeacher(teacher);
        if (tid<0) {
			addActionError("用户名或密码错误");
			return ERROR;
		}
        //super.session.put("teacherId", tid);
        super.getSession().put("teacherId", tid);
        super.getSession().put("teacherName", this.tManager.getTeacher(tid).getName());
        super.getSession().put("user", "teacher");
        return SUCCESS;
    }
    
    public String loginAdmin() {   			   

    	List<Teacher> list = tManager.getTeachers();
    	boolean hasAdmin = false;
    	for(Teacher teacher : list) {
    		if (teacher.isAdmin()) {
				hasAdmin = true;
			}
    	}
    	
    	if (!hasAdmin) {
			if (this.number.equals("123456") && this.password.equals("123456")) {
//				getSession().put("noAdmin", "true");
				super.getSession().put("adminId", "-1");
				getSession().put("adminName", "初始管理员");
				super.getSession().put("user", "admin");
				return SUCCESS;
			}else {
				return INPUT;
			}
		}
    	
    	Teacher admin = new Teacher();
    	admin.setNumber(this.number);
    	
    	admin.setPassword(this.password);
        int tid = this.tManager.checkAdmin(admin);
        if (tid<0) {
			addActionError("用户名或密码错误");
			return ERROR;
		}
        
        //this.session.put("teacherId", tid);      
        super.getSession().put("adminId", tid);
        super.getSession().put("adminName", this.tManager.getTeacher(tid).getName());
        super.getSession().put("user", "admin");
        return SUCCESS;
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

}