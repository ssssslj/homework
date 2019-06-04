package action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import service.AnswerManager;
import service.BindingManager;
import service.ExamManager;
import service.SimgManager;
import service.StudentManager;
import service.TeacherManager;

public class BaseAction  extends ActionSupport{

	protected Map<String,Object> session;
	protected Map<String, Object> application;
	
	protected AnswerManager aManager;
	protected BindingManager bManager;
	protected ExamManager eManager;
	protected StudentManager sManager;
	protected TeacherManager tManager;
	protected SimgManager iManager;
	
	public Map<String, Object> getSession() {
		
		return ActionContext.getContext().getSession();
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Map<String, Object> getApplication() {
		return ActionContext.getContext().getApplication();
	}
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
	public AnswerManager getaManager() {
		return aManager;
	}
	public void setaManager(AnswerManager aManager) {
		this.aManager = aManager;
	}
	public BindingManager getbManager() {
		return bManager;
	}
	public void setbManager(BindingManager bManager) {
		this.bManager = bManager;
	}
	public ExamManager geteManager() {
		return eManager;
	}
	public void seteManager(ExamManager eManager) {
		this.eManager = eManager;
	}
	public StudentManager getsManager() {
		return sManager;
	}
	public void setsManager(StudentManager sManager) {
		this.sManager = sManager;
	}
	public TeacherManager gettManager() {
		return tManager;
	}
	public void settManager(TeacherManager tManager) {
		this.tManager = tManager;
	}
	public SimgManager getiManager() {
		return iManager;
	}
	public void setiManager(SimgManager iManager) {
		this.iManager = iManager;
	}
	
	
	
	
}
