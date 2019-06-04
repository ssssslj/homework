package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import listener.ExamListener;
import service.ExamManager;
import service.TeacherManager;
import util.ComparatorUtil;
import util.Config1;
import util.ConfigUtil;
import util.FileUtil;
import util.SubmitExporterUtil;
import util.ZipDownloadUtil;
import vo.Answer;
import vo.Exam;
import vo.Student;
import vo.Teacher;

/**
 * 
 */
public class TeacherExamAction extends BaseAction {

	private Integer eid;
	private String ename;
	private Date etime;
	private boolean eautostart;                                
	
	private File paper;
	private String paperFileName;
	private InputStream inputStream;
	
	private Exam exam;
	private List<Exam> examList;

	private String zipName;                               //
	private int maxfilesize;                             //
	private String xlsName;                                    //
	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Date getEtime() {
		return etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public boolean isEautostart() {
		return eautostart;
	}

	public void setEautostart(boolean eautostart) {
		this.eautostart = eautostart;
	}

	public File getPaper() {
		return paper;
	}

	public void setPaper(File paper) {
		this.paper = paper;
	}

	

	public String getPaperFileName() {
		
//		this.exam = eManager.getExam(this.eid);
//		this.paperFileName = exam.getPaper();
		
		return paperFileName;
	}

	public void setPaperFileName(String paperFileName) {
		this.paperFileName = paperFileName;
	}
	
	

	public InputStream getInputStream() {
		
//		this.exam = eManager.getExam(this.eid);
//		this.paperFileName = exam.getName();
//		this.inputStream = FileUtil.getPaperInputStreamByExam(exam);
		
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}
	
	

	public List<Exam> getExamList() {
		return examList;
	}
	
	

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}


	public TeacherExamAction() {
	}

	public String teacherMainPage() {

		return SUCCESS;
	}
	
	public String teacherExamBeforePage() {
		
		this.examList = getExamsByTeacher();


		return SUCCESS;
	}
	
	public String teacherExamAfterPage() {
		this.examList = getExamsByTeacher();

		return SUCCESS;
	}

	public String teacherExamNotifyPage() {

		return SUCCESS;
	}
	
	public List<Exam> getExamsByTeacher() {	
		System.out.println("teacherId:  " + (Integer)getSession().get("teacherId"));
		return eManager.getExamsByTeacherId((Integer)getSession().get("teacherId"));
		
//		this.examList = new ArrayList<>();
//		for(Exam exam : teacher.getExams()) {
//			this.examList.add(exam);
//		}
	}

	public String addExam() throws Exception {
		if (this.ename == null) {

		}
		Exam exam = new Exam();
		exam.setName(this.ename);
		this.etime.setSeconds(0);
		exam.setStartTime(this.etime);
		
		exam.setAutoStart(this.eautostart);
		exam.setStarted(false);
		exam.setFinished(false);
		exam.setArchived(false);
		exam.setCleaned(false);
		Teacher teacher = tManager.getTeacher((Integer)this.getSession().get("teacherId"));
		exam.setTeacher(teacher);
		eManager.addExam(exam);
		
		
		return SUCCESS;

	}

	public String editExamPage() {
		this.exam = eManager.getExam(this.eid);
		return SUCCESS;
	}

	public String updateExam() {
		Exam exam = eManager.getExam(this.eid);
		exam.setName(this.ename);
		this.etime.setSeconds(0);
		exam.setStartTime(this.etime);
		exam.setAutoStart(this.eautostart);
		eManager.updateExam(exam);
		this.exam =exam;
		//eManager.updateExam(exam, (Integer) super.getSession().get("teacherId"));

		return SUCCESS;
	}

	public String uploadExamPaper() {
		
		Config1 config=new Config1();                                            //
		 String configPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("") + Config1.CONFIG_FILE;
		File file=new File(configPath);                                             //
		if(!file.exists())   //
		{                                                                              //
			ConfigUtil.writeConfig(file, config);                                      //
		}                                                                             //
		config=ConfigUtil.parseConfig(file);                                          //
		this.maxfilesize=config.getMaxfilesize();                                     //
		if(paper.length()>maxfilesize)                                                 //     
		{                                                                            //
			HttpSession session=ServletActionContext.getRequest().getSession();       //
			session.setAttribute("tipssss", "文件太大，超过了上限");                        //
			session.setAttribute("tipsssss", eid); //
	       return INPUT;                                                        //
		}                           
		
		this.exam = eManager.getExam(this.eid);
		exam.setPaper(this.paperFileName);

		eManager.uploadExamPaper(this.exam, paper);
		//eManager.updateExam(exam);
		return SUCCESS;
	}
	
	public String downloadExamPaper2() {
		this.exam = eManager.getExam(this.eid);
		this.paperFileName = FileUtil.getRightCode(exam.getPaper());
		this.inputStream = FileUtil.getPaperInputStreamByExam(exam);
		return SUCCESS;
	}

	public String startExam() {

		int result = eManager.startExam(this.eid);
		
		getApplication().put("examId", this.eid);
		getApplication().put("examName", eManager.getExam(this.eid).getName());
		getApplication().put("exam_creater", eManager.getExam(this.eid).getTeacher().getId());
		
		return SUCCESS;
	}
	
	public String stopExam() {
		int result = eManager.stopExam(this.eid);
		
		
		
		getApplication().remove("examId");
		getApplication().remove("examName");
		getApplication().remove("exam_creater");
		return SUCCESS;
	}

	//未完成
	public String downloadExamPapers() {
		// 下载试卷操作
		Exam exam = eManager.getExam(this.eid);
		exam.setArchived(true);
		int result = eManager.updateExam(exam);
		this.paperFileName = FileUtil.getRightCode(FileUtil.getExamAnswerPath(exam));
		this.zipName=FileUtil.getExamZipName(exam);
		HttpServletResponse response=ServletActionContext.getResponse();
		try {
			this.inputStream =ZipDownloadUtil.zipDownload(exam,zipName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}catch (Exception e) {
			// TODO: handle exception
			return ERROR;
		}
		System.out.println(inputStream);
		return SUCCESS;
	}
	
	public String downloadAnswers() {
		
		Exam exam = eManager.getExam(this.eid);
		exam.setArchived(true);
		int result = eManager.updateExam(exam);
		this.xlsName=FileUtil.getxlsName(exam);
		 
		 List<Answer> answers = new ArrayList<>();
		 List<Student> students = exam.getStudents();
		 
		 for(Student stu : students) {
			 List<Answer> answers2 = stu.getAnswers();
			 // 
			 if (answers2.size() > 0) {
				 Collections.sort(answers2,new ComparatorUtil("submitTime", true));
				 answers.add(answers2.get(0));
			}	 
		 }    
	     
		File xlsFile= SubmitExporterUtil.doExport(xlsName, answers);
		try {
			this.inputStream=new FileInputStream(xlsFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	// 未完成
	public String cleanExam() {
		// 清理考试操作
		int result = eManager.cleanExam(this.eid);
		return SUCCESS;
	}
	
	public String deleteExam() {
		int result =  eManager.deleteExam(this.eid);
		return SUCCESS;
	}


	public void sendMessage() {
		// TODO implement here
	}

	public void downLoadSubmitInfoPage() {
		// TODO implement here
	}
	public String getZipName() {                       //
		return zipName;                                //
	}                                                  //

	public void setZipName(String zipName) {            //
		this.zipName = zipName;                        //
	}                                                  //
	
	
	public int getMaxfilesize() {                     //
		return maxfilesize;                          //
	}                                                //

	public void setMaxfilesize(int maxfilesize) {        //
		this.maxfilesize = maxfilesize;                //
	}                                                 //

	public String getXlsName() {
		return xlsName;
	}

	public void setXlsName(String xlsName) {
		this.xlsName = xlsName;
	}
	


}