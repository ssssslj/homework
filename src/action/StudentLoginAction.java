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
    	System.out.println("����ִ�е�½");
        Student student = new Student();
        student.setNumber(this.number);
        //Map<String,Object> session = actionContext1.getSession(); 
        Map session = ActionContext.getContext().getSession(); 
        session.put("num",this.number); 
        student.setName(this.name);
        student.setExam(null);
        String ip = ServletActionContext.getRequest().getRemoteAddr();
        int sid = super.sManager.checkStudent(student,(Integer)getApplication().get("examId"));
        if (sid<0) { // ѧ��������Ϣ����
			addActionError("��Ϣ�������");
			return ERROR;
		}
        // ѧ����Ϣ������ȷ����ip�����ж�
        
        // �������ݿ��д�ѧ������Ϣ
        Student student2 = sManager.getStudentById(sid);
        Binding binding = bManager.getBindingByIp(ip);
        if (student2.getBinding() == null) { // ��ѧ����Ӧ��ipΪ�գ�˵��δ��¼
			// ����ip�Ƿ��ѱ�ռ��
 
            if (binding == null) {// ��ip���������ݿ��У���¼�ɹ�
            	binding = new Binding();
            	binding.setStudent(student2);
            	binding.setIp(ip);
            	bManager.addBinding(binding);
            	System.out.println("login: " + 1);
            	// binding�Ƿ���Ҫ����
    		}else {
    			System.out.println("result :" + binding.getIp().equals(ip));
    			System.out.println("bindingIp: " + binding.getIp() + " ip: " + ip);
    			System.out.println("��ip�ѱ�ռ��");
    			addActionError("��ip�ѱ�ռ��");
    			System.out.println("login: " + 2);
    			return INPUT;
    		}
        }else { // ��ѧ���ѵ�¼����ip��ַ����ƥ��
        	
			if (student2.getBinding().getIp().equals(ip)) { // ѧ���ѵ�¼��ip��ȣ���¼�ɹ�
				System.out.println("login: " + 3);
			}else { // ip��ͬ��˵��ѧ����������������¼
				System.out.println("login: " + 5);
				System.out.println("����������������¼");
				addActionError("����������������¼");
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