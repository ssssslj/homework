package action;

import java.util.List;

import vo.Exam;
import vo.Teacher;

/**
 * 
 */
public class AdminExamAction extends BaseAction{

	private Integer eid;
	
	private List<Exam> examList;
	
	
	
    public Integer getEid() {
		return eid;
	}


	public void setEid(Integer eid) {
		this.eid = eid;
	}


	public List<Exam> getExamList() {
		return examList;
	}


	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}


	public AdminExamAction() {
    }

    
    public String adminMainPage() {
    	
    	return SUCCESS;
    }
    
    public String adminExamPage() {
    	this.examList = getExams();
    	return SUCCESS;
    }
    
    public List<Exam> getExams(){
    	return eManager.getAllExams();
    }
    
    public String cleanExam() {
		// ÇåÀí¿¼ÊÔ²Ù×÷
		int result =  eManager.cleanExam(this.eid);
		return SUCCESS;
	}
    
    public String deleteExam() {
		int result =  eManager.deleteExam(this.eid);
		return SUCCESS;
	}

   

}