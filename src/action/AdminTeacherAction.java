package action;

import java.util.List;

import bean.PageBean;
import util.EncryptUtil;
import vo.Teacher;

/**
 * 
 */
public class AdminTeacherAction extends BaseAction{

	private Integer id;
	private String number;
	private String password;
	private String name;
	private boolean admin; // checkbox选中与否对应布尔值
	
	private List<Teacher> teacherList;
	
	private PageBean<Teacher> pageBean;


	public PageBean<Teacher> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Teacher> pageBean) {
		this.pageBean = pageBean;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("name:");
		this.name = name;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public AdminTeacherAction() {
    }

    public String adminTeacherPage(){
    	
    	if (pageBean == null) {
    		System.out.println("pageBean: " + pageBean);
    		pageBean = tManager.getTeacherPage(1);
    		
		}else {
			pageBean = tManager.getTeacherPage(pageBean.getPage());
			System.out.println("pageNo: " + pageBean.getPage());
		}
    	System.out.println("list: " + pageBean.getList());
    	System.out.println("pageSize: " + pageBean.getPageSize());
    	System.out.println("pageNo: " + pageBean.getPage());
    	System.out.println("pageTotal: " + pageBean.getTotalCount());
    	this.teacherList =  pageBean.getList();
    	return SUCCESS;
    }

    public String serachTeacher() {
    	System.out.println("pageBean1: " + pageBean);
    	pageBean = tManager.getTeacherPage(pageBean.getPage());
    	this.teacherList =  pageBean.getList();
    	return SUCCESS;
    }
    
    
    /**
     * 
     */
    public String addTeacher() {
        Teacher teacher = new Teacher();
        teacher.setNumber(this.number);
        teacher.setPassword(EncryptUtil.Encoder(this.password));
        teacher.setName(this.name);
        teacher.setAdmin(this.admin); 
        System.out.println("admin: " + this.admin);
        
        try {
        	int result =  tManager.addTeacher(teacher);
		} catch (Exception e) {
			
			System.out.println("教师添加失败");		}
    	return SUCCESS;
    }

    /**
     * 
     */
    public String modifyTeacherPage() {
        Teacher teacher = tManager.getTeacher(this.id);
        if (teacher==null) {
			return ERROR;
		}
        this.id = teacher.getId();
        this.number = teacher.getNumber();
        this.name = teacher.getName();
        this.admin = teacher.isAdmin();
    	return SUCCESS;
    }
    
    public String updateTeacher() {
    	Teacher teacher = tManager.getTeacher(this.id);
    	//System.out.println("id:" + id);
    	teacher.setNumber(this.number);
    	teacher.setName(this.name);
    	teacher.setPassword(EncryptUtil.Encoder(this.password));
    	teacher.setAdmin(this.admin);
    	System.out.println("admin:" + admin);
    	
        int result = tManager.updateTeacher(teacher);
    	return SUCCESS;
    }

    /**
     * 
     */
    public String deleteTeacher() {
    	Teacher teacher = tManager.getTeacher(this.id);
    	tManager.deleteTeacher(teacher);
    	return SUCCESS;
    }

}