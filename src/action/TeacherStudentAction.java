package action;

import java.io.File;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.ComparatorUtil;
import vo.Exam;
import vo.Student;

/**
 * 
 */
public class TeacherStudentAction extends BaseAction {

	private File studentsFile; // 学生名单文件
	private String studentsFileContentType; // 文件类型
	private String sutdentsFileFileName; // 文件名
	private int id;
	private String sno;
	private String sname;
	private String sclass;
	private Exam exam;
	
	private String snumber;
	private String ip;
	private List<Student> studentList;


	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public String getSnumber() {
		return snumber;
	}

	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public File getStudentsFile() {
		return studentsFile;
	}

	public void setStudentsFile(File studentsFile) {
		this.studentsFile = studentsFile;
	}

	public String getStudentsFileContentType() {
		return studentsFileContentType;
	}

	public void setStudentsFileContentType(String studentsFileContentType) {
		this.studentsFileContentType = studentsFileContentType;
	}
	
	

	public String getSutdentsFileFileName() {
		return sutdentsFileFileName;
	}

	public void setSutdentsFileFileName(String sutdentsFileFileName) {
		this.sutdentsFileFileName = sutdentsFileFileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}


	public TeacherStudentAction() {
	}
	
	public String examStudentPage() {
		
		return SUCCESS;
	}

	 
	public String importStudent() {
		this.exam = eManager.getExam(this.exam.getId());
		
		//super.sManager.importStudents(this.studentsFile, this.studentsFileContentType);
		System.out.println("examAction: " + this.exam);
		sManager.importStudents(this.exam, this.studentsFile);
		
		return SUCCESS;

	}


	public String getStudentByStudent2() {
		Integer examId = examId = exam.getId();
		
		List<Student> students = new ArrayList<>();
		if (this.snumber != null && !this.snumber.equals("")) {
			students = sManager.getStudentByNumber(this.snumber);
		}else if (this.sname != null && !this.sname.equals("")) {
			students = sManager.getStudentByName(this.sname);
		}else if(this.sclass != null && !this.sclass.equals("")){
			students = sManager.getStudentsByClass(this.sclass);
		}else {
			students = sManager.getStudentsByExamId(examId);
			
		}
//		studentList.sort(new ComparatorUtil("id",true));
		
		this.studentList = new ArrayList<>();
		if (students == null) { // 未查询到学生
			return SUCCESS;
		}
		for(Student student : students) {
			if (student.getExam().getId() == examId) {
				this.studentList.add(student);
			}
		}
		Collections.sort(this.studentList, new ComparatorUtil("number", false));
		//studentList.sort(c);
		//this.studentList = students;
		System.out.println(this.studentList);
		if (studentList == null || studentList.size()<1) {
			return SUCCESS; // 查询失败
		}
		return SUCCESS;
	}
	
	public String addStudent() {
		
		Student student = new Student();
		student.setNumber(this.snumber);
		student.setName(this.sname);
		student.setSclass(this.sclass);
		
		Integer examId = examId = exam.getId();
		
		int result = sManager.addStudent(student,eManager.getExam(examId));
		
		if (result < 0) {
			//addActionMessage("此学生已存在");
		}
		
		return SUCCESS;
	}
	
	
	

}