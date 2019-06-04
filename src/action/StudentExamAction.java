package action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import util.ComparatorUtil;
import util.Config1;
import util.ConfigUtil;
import util.FileUtil;
import vo.Answer;
import vo.Exam;
import vo.Student;

/**
 * 
 */
public class StudentExamAction extends BaseAction{

	private InputStream inputStream;
	private Exam exam;
	private String paperFileName;
	
	private File answer;
	private String answerFileName;
	
	 private int maxfilesize1;                       //
	private List<Answer> answerList;
    
    public StudentExamAction() {
    }

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public String getAnswerFileName() {
		return answerFileName;
	}

	public void setAnswerFileName(String answerFileName) {
		this.answerFileName = answerFileName;
	}

	public File getAnswer() {
		return answer;
	}

	public void setAnswer(File answer) {
		this.answer = answer;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getPaperFileName() {
		return paperFileName;
	}

	public void setPaperFileName(String paperFileName) {
		this.paperFileName = paperFileName;
	}

	public String studentMainPage() {
		this.exam = eManager.getExam((Integer)getApplication().get("examId"));
    	return SUCCESS;
    }
    
    public String studentSummitPage() {
    	Student student = sManager.getStudentById((Integer)getSession().get("studentId"));
    	this.answerList = new ArrayList<>();
    	for(Answer answer : student.getAnswers()) {
    		this.answerList.add(answer);
    	}
    	// 按提交时间排序
    	Collections.sort(this.answerList,new ComparatorUtil("submitTime", false));
    	return SUCCESS;
    }
    
    public String studentExit() {
    	System.out.println("studentExit");
    	getSession().remove("studentId");
    	getSession().remove("studentName");
    	
    	return SUCCESS;
    }


    public String downloadExamPaper() {
    	this.exam = eManager.getExam(this.exam.getId());
		this.paperFileName = FileUtil.getRightCode(exam.getPaper());
		this.inputStream = FileUtil.getPaperInputStreamByExam(exam);
		return SUCCESS;
    }

    /**
     * 
     */
    public String uploadAnswer() {
    	
    	Config1 config=new Config1();                                            //
		 String configPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("") + Config1.CONFIG_FILE;
		File file=new File(configPath);                                             //
		if(!file.exists())   //
		{                                                                              //
			ConfigUtil.writeConfig(file, config);                                      //
		}                                                                             //
		config=ConfigUtil.parseConfig(file);                                          //
		this.maxfilesize1=config.getMaxfilesize();
		String[] strArray = answerFileName.split("\\.");
       int suffixIndex = strArray.length -1;//
       if(answer.length()>maxfilesize1&&!strArray[suffixIndex].equals("zip"))
       {
       	HttpSession session=ServletActionContext.getRequest().getSession();       //
			session.setAttribute("tipss", "文件太大，超过了上限");                        //
           session.setAttribute("tipsss", "不支持上传该类型的文件");                        //
			
			return INPUT;                                                           //
       }
		if(answer.length()>maxfilesize1)                                                 //     
		{                                                                            //
			HttpSession session=ServletActionContext.getRequest().getSession();       //
			session.setAttribute("tipss", "文件太大，超过了上限");                        //
			
			return INPUT;                                                            //
		}                                                                                 //
		
       if(!strArray[suffixIndex].equals("zip"))                                                 //     
		{                                                                            //
			HttpSession session=ServletActionContext.getRequest().getSession();       //
			session.setAttribute("tipsss", "不支持上传该类型的文件");                        //
			
			return INPUT;                                                            //
		}   
    	
    	System.out.println("application: " + getApplication());
    	this.exam = eManager.getExam((Integer)getApplication().get("examId"));
    	Student student = sManager.getStudentById((Integer)getSession().get("studentId"));
    	
		eManager.uploadExamAnswer(this.exam, student,answer,this.answerFileName);
    	return SUCCESS;
    }

    public int getMaxfilesize1() {                                //
		return maxfilesize1;                                     //
	}

	public void setMaxfilesize1(int maxfilesize1) {              //
		this.maxfilesize1 = maxfilesize1;                         //
	}

}