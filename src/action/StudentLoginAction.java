package action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionContext;

import vo.Binding;
import vo.Exam;
import vo.Student;

/**
 * 
 */
public class StudentLoginAction extends BaseAction {

    private String number;
    private String name;
    private Integer eid;
    private ActionContext actionContext;
    private ActionContext actionContext1 =new ActionContext(getSession());
	private Exam exam;
    
    public StudentLoginAction() {
    }


    public Exam getExam() {
		return exam;
	}


	public void setExam(Exam exam) {
		this.exam = exam;
	}


	public String getLoginPage() {
    	if (getApplication().get("examId") == null) {
    		List<Exam> exams = eManager.getAllExams();
    		System.out.println("examS:" + exams);
    		for(Exam exam : exams) {
    			if (exam.getStarted() && !exam.getFinished()) {
					getApplication().put("examId", exam.getId());
					getApplication().put("examName", exam.getName());
					getApplication().put("exam_creater", exam.getTeacher().getId());
				}
    		}
		}
    	
    	return SUCCESS;
    }
    
    

    public ActionContext getActionContext() {
		return actionContext;
	}


	public void setActionContext(ActionContext actionContext) {
		this.actionContext = actionContext;
	}


	/**
     * 
     */
    public String login() {
    	System.out.println("正在执行登陆");
        Student student = new Student();
        student.setNumber(this.number);
        //Map<String,Object> session = actionContext1.getSession(); 
        Map session = ActionContext.getContext().getSession(); 
        session.put("num",this.number); 
        student.setName(this.name);
        student.setExam(null);
        String ip = ServletActionContext.getRequest().getRemoteAddr();
        int sid = super.sManager.checkStudent(student,(Integer)getApplication().get("examId"));
        if (sid<0) { // 学生输入信息有误
			addActionError("信息输入错误");
			return ERROR;
		}
        // 学生信息输入正确，对ip进行判断
        
        // 加载数据库中此学生的信息
        Student student2 = sManager.getStudentById(sid);
        Binding binding = bManager.getBindingByIp(ip);
        if (student2.getBinding() == null) { // 此学生对应的ip为空，说明未登录
			// 看此ip是否已被占用
 
            if (binding == null) {// 此ip不存在数据库中，登录成功
            	binding = new Binding();
            	binding.setStudent(student2);
            	binding.setIp(ip);
            	bManager.addBinding(binding);
            	System.out.println("login: " + 1);
            	// binding是否需要保存
    		}else {
    			System.out.println("result :" + binding.getIp().equals(ip));
    			System.out.println("bindingIp: " + binding.getIp() + " ip: " + ip);
    			System.out.println("此ip已被占用");
    			addActionError("此ip已被占用");
    			System.out.println("login: " + 2);
    			return INPUT;
    		}
        }else { // 此学生已登录，对ip地址进行匹配
        	
			if (student2.getBinding().getIp().equals(ip)) { // 学生已登录，ip相等，登录成功
				System.out.println("login: " + 3);
			}else { // ip不同，说明学生已在其他机器登录
				System.out.println("login: " + 5);
				System.out.println("你已在其他机器登录");
				addActionError("你已在其他机器登录");
				return INPUT;
			}
		}   
       getSession().put("studentId", sid);
       getSession().put("studentName", this.name);
       super.getSession().put("user", "student");
       this.exam = eManager.getExam((Integer)getApplication().get("examId"));

        return SUCCESS;
    }

    /**
     * 
     */
    public void exit() {
            }

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEid() {
		return eid;
	}


	public void setEid(Integer eid) {
		this.eid = eid;
	}
    
    

}