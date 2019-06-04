package action;

public class TeacherExitAction extends BaseAction {

	public String adminExit() {
		super.getSession().remove("adminId");
		super.getSession().remove("adminName");
		return SUCCESS;
	}
	
	public String teacherExit() {
		super.getSession().remove("teacherId");
		super.getSession().remove("teacherName");
		return SUCCESS;
	}
}
